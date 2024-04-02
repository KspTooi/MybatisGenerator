package com.ksptooi.generator;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import com.ksptooi.utils.TextConv;
import com.ksptooi.utils.VelocityWrapper;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.List;

public class GeneratorMapper implements Generator{

    @Override
    public boolean enable(MtgGenOptions opt) {
        return opt.isGenMapper();
    }

    @Override
    public void generate(MtgGenOptions opt, List<TableField> fields) {

        final VelocityContext vc = new VelocityContext();

        vc.put("voName",opt.getVoName());
        vc.put("fieldVoName", TextConv.toJavaFiled(opt.getVoName()));
        vc.put("pkgNameVo",opt.getPkgNameVo());

        vc.put("poName",opt.getPoName());
        vc.put("fieldPoName",TextConv.toJavaFiled(opt.getPoName()));
        vc.put("pkgNamePo",opt.getPkgNamePo());

        vc.put("serviceImplName",opt.getServiceImplName());
        vc.put("pkgNameServiceImpl",opt.getPkgNameServiceImpl());

        vc.put("mapperName",opt.getMapperName());
        vc.put("fieldMapperName",TextConv.toJavaFiled(opt.getMapperName()));
        vc.put("pkgNameMapper",opt.getPkgNameMapper());

        vc.put("fields",fields);

        vc.put("tableName",opt.getTableName());
        vc.put("fieldsByTable",fields);

        String tName = "mapper.ftl";
        String tXmlName = "mapper_xml.ftl";

        if(opt.isEnableMybatisPlus()){
            tName = "mapper_plus.ftl";
            tXmlName = "mapper_plus_xml.ftl";
        }

        File out = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNameMapper()) + "\\"+opt.getMapperName() + ".java");
        Template t = VelocityWrapper.getTemplate(tName);
        VelocityWrapper.mergeAndOutput(t,vc,out);

        out = new File(opt.getOutputPath().getParent(), "\\resources\\mapper" + "\\"+opt.getMapperName() + ".xml");
        t = VelocityWrapper.getTemplate(tXmlName);
        VelocityWrapper.mergeAndOutput(t,vc,out);
    }

    @Override
    public String getName() {
        return "Mapper类生成器";
    }
}
