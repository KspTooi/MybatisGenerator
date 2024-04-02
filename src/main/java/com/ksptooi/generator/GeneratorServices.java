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

        Template t = VelocityWrapper.getTemplate("service_impl.vm");

        VelocityContext vc = VelocityWrapper.injectContext(opt, fields);

        File out = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNameServiceImpl()) + "\\"+opt.getServiceImplName() + ".java");
        VelocityWrapper.mergeAndOutput(t,vc,out);
    }

    @Override
    public String getName() {
        return "服务类生成器";
    }
}
