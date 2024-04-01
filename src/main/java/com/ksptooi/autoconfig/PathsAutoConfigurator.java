package com.ksptooi.autoconfig;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.List;

public class PathsAutoConfigurator implements AutoConfigurator{

    private final Logger log = LoggerFactory.getLogger(PathsAutoConfigurator.class);
    @Override
    public void doAutomaticConfiguration(Connection conn, MtgGenOptions opt, List<TableField> fields) {

        if(opt.getOutputPath() == null){

            //已配置项目名称
            if(StringUtils.isNotBlank(opt.getProjectName())){

                String path = System.getProperty("user.dir");
                Path parent = Paths.get(path).getParent();

                if(isMavenProject(parent.toAbsolutePath().toString() + "\\" + opt.getProjectName())){
                    path = parent + "\\" + opt.getProjectName() + "\\src\\main\\java";
                    opt.setOutputPath(new File(path));
                    log.info("[自动配置]已识别到Maven项目 配置输出路径为:{}",path);
                    return;
                }

                log.warn("自动配置失败 当前配置的项目不存在:{}",opt.getProjectName());
            }

            //未配置项目名
            String path = System.getProperty("user.dir");

            if(isMavenProject(path)){
                path = path + "\\src\\main\\java";
                log.info("[自动配置]已识别到Maven项目 配置输出路径为:{}",path);
                opt.setOutputPath(new File(path));
                return;
            }

            log.info("[自动配置]输出路径为:{}",path);
            opt.setOutputPath(new File(path));
        }

    }

    public boolean isMavenProject(String path){
        boolean hasSrc = Files.exists(Paths.get(path + "\\src"));
        boolean hasMain = Files.exists(Paths.get(path + "\\src\\main"));
        boolean hasJava = Files.exists(Paths.get(path + "\\src\\main\\java"));
        return hasSrc && hasMain && hasJava;
    }

    @Override
    public String getName() {
        return "路径自动配置器";
    }
}
