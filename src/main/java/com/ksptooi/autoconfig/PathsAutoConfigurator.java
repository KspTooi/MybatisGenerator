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

            String projectName = opt.getProjectName();

            //已配置项目名称
            if(StringUtils.isNotBlank(projectName)){

                String path = System.getProperty("user.dir");

                File mavenProject = findMavenProject(new File(path), projectName);

                if(mavenProject == null){
                    log.error("自动配置失败,在所有路径中都无法查找到项目:{}", projectName);
                    throw new RuntimeException("自动配置失败,在所有路径中都无法查找到项目:"+projectName);
                }

                File javaPath = new File(mavenProject,"\\src\\main\\java");
                opt.setOutputPath(javaPath);
                log.info("[自动配置]已识别到Maven项目 配置输出路径为:{}",javaPath.getAbsolutePath());
                return;
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

    /**
     * 递归向上查找父级路径下是否存在Maven项目
     */
    public File findMavenProject(File path,String projectName){

        if(path == null){
            return null;
        }

        //当前路径是否有项目
        File f = new File(path,projectName);

        if(f.exists() && isMavenProject(f.getAbsolutePath())){
            return f;
        }

        //递归查找上级
        return findMavenProject(path.getParentFile(),projectName);
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
