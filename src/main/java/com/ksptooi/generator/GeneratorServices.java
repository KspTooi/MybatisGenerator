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
        File out = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNamePo()) + "\\"+opt.getPoName() + ".java");
        Template t = VelocityWrapper.getTemplate("po.ftl");
        VelocityWrapper.mergeAndOutput(t,vc,out);
    }

    @Override
    public String getName() {
        return "服务类生成器";
    }
}
