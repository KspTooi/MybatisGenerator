package com.ksptooi.utils;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
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
import java.util.List;
import java.util.Properties;

public class VelocityWrapper {

    private static final Logger log = LoggerFactory.getLogger(VelocityWrapper.class);

    private static VelocityEngine ve = null;

    private static String namespace = null;

    private static MtgGenOptions opt = null;

    public static void init(String path, MtgGenOptions opt){
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
        VelocityWrapper.opt = opt;
    }

    public static void setNamespace(String ns){
        namespace = ns;
    }

    public static Template getTemplate(String name){

        if(namespace == null){
            throw new RuntimeException("模板获取失败 没有配置有效的namespace");
        }

        return ve.getTemplate(namespace+File.separator+name);
    }


    /**
     * 注入OPT到VC中
     * @return
     */
    public static VelocityContext injectContext(MtgGenOptions opt, List<TableField> fields){

        VelocityContext v = new VelocityContext();

        v.put("genController",opt.isGenController());
        v.put("genService",opt.isGenService());
        v.put("withImpl",opt.isWithImpl());
        v.put("genPo",opt.isGenPo());
        v.put("genVo",opt.isGenVo());
        v.put("genMapper",opt.isGenMapper());

        //大写的类名 eg:: GenericController
        v.put("controllerName", opt.getControllerName());
        v.put("serviceName", opt.getServiceName());
        v.put("serviceImplName", opt.getServiceImplName());
        v.put("voName", opt.getVoName());
        v.put("poName", opt.getPoName());
        v.put("mapperName", opt.getMapperName());

        //顶级包名 eg:: com.ksptooi.app
        v.put("packetName", opt.getPacketName());

        //全包名 eg::com.ksptooi.app.generic
        v.put("pkgNameController", opt.getPkgNameController());
        v.put("pkgNameService", opt.getPkgNameService());
        v.put("pkgNameServiceImpl", opt.getPkgNameServiceImpl());
        v.put("pkgNameVo", opt.getPkgNameVo());
        v.put("pkgNamePo", opt.getPkgNamePo());
        v.put("pkgNameMapper", opt.getPkgNameMapper());
        v.put("pkgNameMapperXml", opt.getPkgNameMapperXml());

        //字段类名 eg:: genericController
        v.put("fieldControllerName", TextConv.toJavaFiled(opt.getControllerName()));
        v.put("fieldServiceName", TextConv.toJavaFiled(opt.getServiceName()));
        v.put("fieldServiceImplName", TextConv.toJavaFiled(opt.getServiceImplName()));
        v.put("fieldVoName", TextConv.toJavaFiled(opt.getVoName()));
        v.put("fieldPoName", TextConv.toJavaFiled(opt.getPoName()));
        v.put("fieldMapperName", TextConv.toJavaFiled(opt.getMapperName()));


        //表名 eg:: generic_table
        v.put("tableName", opt.getTableName());
        //大写的表名 eg:: GenericTable
        v.put("classTableName", TextConv.toJavaClass(opt.getTableName()));

        v.put("primaryField", opt.getPrimaryField());

        v.put("enableLombok", opt.isEnableLombok());
        v.put("enableSlf4J", opt.isEnableSlf4J());
        v.put("enableSwagger2", opt.isEnableSwagger2());
        v.put("enableMybatisPlus", opt.isEnableMybatisPlus());

        //数据库的全部列
        v.put("fields",fields);
        return v;
    }


    public static void mergeAndOutput(Template t, VelocityContext vc, File output){

        if(output.exists()){
            if(!opt.isSilence()){
                log.info("移除文件:{}",output.getAbsolutePath());
            }
        }

        File dir = output.getParentFile();

        if(!dir.exists()){
            if(!opt.isSilence()){
                log.info("创建文件夹:{}",dir.getAbsolutePath());
            }
            dir.mkdirs();
        }

        StringWriter writer = new StringWriter();
        t.merge(vc, writer);


        Path outputPath = Paths.get(output.getPath());

        // create and write new file with contents from writer
        try (FileWriter fileWriter = new FileWriter(outputPath.toFile())) {
            fileWriter.write(writer.toString());
            if(!opt.isSilence()){
                log.info("写出至:{}",output.getAbsolutePath());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
