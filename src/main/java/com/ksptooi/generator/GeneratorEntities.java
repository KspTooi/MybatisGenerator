package com.ksptooi.generator;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import com.ksptooi.utils.TextConv;
import com.ksptooi.utils.VelocityWrapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import java.io.File;
import java.util.List;

public class GeneratorEntities implements Generator{

    @Override
    public boolean enable(MtgGenOptions opt) {
        return opt.isGenPo();
    }


    @Override
    public void generate(MtgGenOptions opt, List<TableField> fields) {

        final boolean hasPk = StringUtils.isNotBlank(opt.getPrimaryField());
        TableField pk = TableField.getPrimary(fields);

        final VelocityContext vc = new VelocityContext();

        vc.put("poName",opt.getPoName());
        vc.put("packetNamePo",opt.getPkgNamePo());
        vc.put("fields",fields);
        vc.put("enableLombok",opt.isEnableLombok());
        vc.put("enableSwagger2",opt.isEnableSwagger2());

        File out = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNamePo()) + "\\"+opt.getPoName() + ".java");
        Template t = VelocityWrapper.getTemplate("po.ftl");
        VelocityWrapper.mergeAndOutput(t,vc,out);

        if(opt.isGenVo()){
            vc.put("poName",opt.getVoName()+"InVo");
            out = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNameVo()) + "\\"+opt.getVoName() + "InVo.java");
            VelocityWrapper.mergeAndOutput(t,vc,out);
            vc.put("poName",opt.getVoName()+"OutVo");
            out = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNameVo()) + "\\"+opt.getVoName() + "OutVo.java");
            VelocityWrapper.mergeAndOutput(t,vc,out);
        }
    }

    @Override
    public String getName() {
        return "实体类生成器";
    }
}
