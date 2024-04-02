package com.ksptooi.generator;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import com.ksptooi.utils.TextConv;
import com.ksptooi.utils.VelocityWrapper;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Text;

import java.io.File;
import java.util.List;

public class GeneratorController implements Generator{

    private final Logger log = LoggerFactory.getLogger(GeneratorController.class);

    @Override
    public boolean enable(MtgGenOptions opt) {
        return opt.isGenController();
    }

    @Override
    public void generate(MtgGenOptions opt, List<TableField> fields) {

        Template t = VelocityWrapper.getTemplate("controller.vm");
        VelocityContext vc = VelocityWrapper.injectContext(opt, fields);

        File out = new File(opt.getOutputPath(),TextConv.pkgToPath(opt.getPkgNameController())+"\\"+opt.getControllerName()+".java");
        VelocityWrapper.mergeAndOutput(t,vc,out);
    }

    @Override
    public String getName() {
        return "控制器生成工厂";
    }

}
