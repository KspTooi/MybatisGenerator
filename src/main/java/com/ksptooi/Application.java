package com.ksptooi;

import com.ksptooi.config.ConfigFactory;
import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.config.MtgDataSource;
import java.io.File;

public class Application {

    public static void main(String[] args) {

        String currentDir = System.getProperty("user.dir");
        System.out.println("项目启动路径：" + currentDir);

        MtgDataSource ds = ConfigFactory.datasource()
                .driver("com.mysql.cj.jdbc.Driver")
                .host("127.0.0.1:3306")
                .username("root")
                .password("root")
                .params("?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8")
                .dbName("skinserver")
                .templatePath("C:\\InternalDeveloper\\KspTooiProjects\\MysqlTableGenerator\\src\\main\\resources\\freemarker")
                .build();

        MtgGenOptions opt = ConfigFactory.config()
                .genController(true)
                .genService(true)
                .withImpl(true)
                .genPo(true)
                .genMapper(true)
                .packetName("com.ksptooi.app")
                .tableName("jobs")
                .projectName("zsk-basic-service")
                .enableLombok(true)
                .enableSwagger2(true)
                .build();


        MtGenerator mtg = new MtGenerator(ds,opt);
        mtg.generate();

        //mtg.generate();

    }


}
