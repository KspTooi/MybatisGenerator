package com.ksptooi.generator;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import com.ksptooi.utils.VelocityWrapper;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneratorController implements Generator{

    private final Logger log = LoggerFactory.getLogger(GeneratorController.class);

    @Override
    public boolean enable(MtgGenOptions opt) {
        return opt.isGenController();
    }

    @Override
    public void generate(MtgGenOptions opt, TableField field) {

        final VelocityContext vc = new VelocityContext();
        Template t = VelocityWrapper.getTemplate("controller.ftl");


    }

    @Override
    public String getName() {
        return "控制器生成工厂";
    }

}
