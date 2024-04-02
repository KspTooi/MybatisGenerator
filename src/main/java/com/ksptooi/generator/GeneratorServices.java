package com.ksptooi.generator;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import com.ksptooi.utils.TextConv;
import com.ksptooi.utils.VelocityWrapper;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import java.io.File;
import java.util.List;

public class GeneratorServices implements Generator{
    @Override
    public boolean enable(MtgGenOptions opt) {
        return opt.isGenService();
    }

    @Override
    public void generate(MtgGenOptions opt, List<TableField> fields) {

        final VelocityContext vc = new VelocityContext();

        vc.put("voName",opt.getVoName());
        vc.put("fieldVoName",TextConv.toJavaFiled(opt.getVoName()));
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


        File out = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNameServiceImpl()) + "\\"+opt.getServiceImplName() + ".java");
        Template t = VelocityWrapper.getTemplate("service_impl.ftl");

        if(opt.isEnableMybatisPlus()){
            t = VelocityWrapper.getTemplate("service_impl_plus.ftl");
        }

        VelocityWrapper.mergeAndOutput(t,vc,out);
    }

    @Override
    public String getName() {
        return "服务类生成器";
    }
}
