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


        Template t = VelocityWrapper.getTemplate("po.vm");
        VelocityContext vc = VelocityWrapper.injectContext(opt, fields);

        File out = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNamePo()) + "\\"+opt.getPoName() + ".java");

        VelocityWrapper.mergeAndOutput(t,vc,out);

        if(opt.isGenVo()){

            t = VelocityWrapper.getTemplate("vo.vm");

            out = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNameVo()) + "\\"+opt.getVoName() + "InVo.java");
            VelocityWrapper.mergeAndOutput(t,vc,out);

            out = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNameVo()) + "\\"+opt.getVoName() + "OutVo.java");
            VelocityWrapper.mergeAndOutput(t,vc,out);

        }
    }

    @Override
    public String getName() {
        return "实体类生成器";
    }
}
