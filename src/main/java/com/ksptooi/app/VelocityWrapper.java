package com.ksptooi.app;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class VelocityWrapper {

    private static final Logger log = LoggerFactory.getLogger(VelocityWrapper.class);

    private static VelocityEngine ve = null;

    public static void init(String path){
        final Properties properties = new Properties();
        properties.setProperty("resource.loader", "file");
        properties.setProperty("file.resource.loader.description", "Velocity File Resource Loader");
        properties.setProperty("file.resource.loader.path", path);
        properties.setProperty("file.resource.loader.cache", "true");
        properties.setProperty("file.resource.loader.modificationCheckInterval", "30");
        properties.setProperty("directive.set.null.allowed", "true");
        final VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.init(properties);
        ve = velocityEngine;
    }

    public static Template getTemplate(String name){
        return ve.getTemplate(name);
    }


    public static void mergeAndOutput(Template t, VelocityContext vc, File output){

        if(output.exists()){
            log.info("移除文件:{}",output.getAbsolutePath());
        }

        File dir = output.getParentFile();

        if(!dir.exists()){
            log.info("创建文件夹:{}",dir.getAbsolutePath());
            dir.mkdirs();
        }

        StringWriter writer = new StringWriter();
        t.merge(vc, writer);


        Path outputPath = Paths.get(output.getPath());

        // create and write new file with contents from writer
        try (FileWriter fileWriter = new FileWriter(outputPath.toFile())) {
            fileWriter.write(writer.toString());
            log.info("写出文件成功:{}",output.getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
