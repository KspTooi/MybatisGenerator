package com.ksptooi.autoconfig;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;

public class PathsAutoConfigurator implements AutoConfigurator{

    private final Logger log = LoggerFactory.getLogger(PathsAutoConfigurator.class);
    @Override
    public void doAutomaticConfiguration(Connection conn, MtgGenOptions opt, List<TableField> fields) {

        if(opt.getOutputPath() == null){

            String path = System.getProperty("user.dir");

            boolean hasSrc = Files.exists(Paths.get(path + "\\src"));
            boolean hasMain = Files.exists(Paths.get(path + "\\src\\main"));
            boolean hasJava = Files.exists(Paths.get(path + "\\src\\main\\java"));

            if(hasSrc && hasMain && hasJava){
                path = path + "\\src\\main\\java";
                log.info("自动配置已识别到Maven项目 配置输出路径为:{}",path);
                opt.setOutputPath(new File(path));
                return;
            }

            log.info("自动配置输出路径为:{}",path);
            opt.setOutputPath(new File(path));
        }

    }

    @Override
    public String getName() {
        return "路径自动配置器";
    }
}
