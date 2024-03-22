package com.ksptooi.app;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import java.util.Properties;

public class VelocityWrapper {

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


}
