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

        Template tMap = VelocityWrapper.getTemplate("mapper.vm");
        Template tXml = VelocityWrapper.getTemplate("mapper_xml.vm");
        VelocityContext vc = VelocityWrapper.injectContext(opt, fields);

        File oMap = new File(opt.getOutputPath(), TextConv.pkgToPath(opt.getPkgNameMapper()) + "\\"+opt.getMapperName() + ".java");
        File oXml = new File(opt.getOutputXmlPath().getAbsolutePath(),opt.getMapperName() + ".xml");

        VelocityWrapper.mergeAndOutput(tMap,vc,oMap);
        VelocityWrapper.mergeAndOutput(tXml,vc,oXml);
    }

    @Override
    public String getName() {
        return "Mapper类生成器";
    }
}
