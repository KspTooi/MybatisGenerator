package com.ksptooi;

import com.ksptooi.config.ConfigFactory;
import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.config.MtgDataSource;
import java.io.File;

public class Application {

    public static void main(String[] args) {

        String currentDir = System.getProperty("user.dir");
        System.out.println("项目启动路径：" + currentDir);

        return;
        /*MtgDataSource ds = ConfigFactory.datasource()
                .driver("com.mysql.cj.jdbc.Driver")
                .dbName("skinserver")
                .username("root")
                .password("root")
                .params("?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8")
                .templatePath("C:\\InternalDev\\KspTooiProject\\MysqlTableGenerator\\src\\main\\resources\\freemarker\\")
                .build();

        MtgGenOptions opt = ConfigFactory.config()
                .genController(true)
                .genService(true)
                .withImpl(true)
                .genPo(true)
                .genMapper(true)
                .packetName("com.ksptooi.app")
                .controllerName(null)
                .serviceName(null)
                .serviceImplName(null)
                .poName(null)
                .mapperName(null)
                .tableName("jobs")
                .build();


        MtGenerator mtg = new MtGenerator(ds,opt);
        mtg.generate();*/
    }


}
