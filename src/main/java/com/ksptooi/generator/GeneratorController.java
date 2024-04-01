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

        final VelocityContext vc = new VelocityContext();
        Template t = VelocityWrapper.getTemplate("controller.ftl");

        vc.put("classTableName", TextConv.toJavaClass(opt.getTableName()));

        vc.put("controllerName",opt.getControllerName());
        vc.put("fieldControllerName", TextConv.toJavaFiled(opt.getControllerName()));

        vc.put("serviceName",opt.getServiceName());
        vc.put("fieldServiceName", TextConv.toJavaFiled(opt.getServiceName()));

        vc.put("poName",opt.getPoName());
        vc.put("fieldPoName",TextConv.toJavaFiled(opt.getPoName()));


        vc.put("pkgNameController",opt.getPkgNameController());
        vc.put("pkgNameService",opt.getPkgNameService());
        vc.put("pkgNamePo",opt.getPkgNamePo());

        File out = new File(opt.getOutputPath(),TextConv.pkgToPath(opt.getPkgNameController())+"\\"+opt.getControllerName()+".java");
        VelocityWrapper.mergeAndOutput(t,vc,out);

    }

    @Override
    public String getName() {
        return "控制器生成工厂";
    }

}
