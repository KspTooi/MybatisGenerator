package com.ksptooi;

import com.ksptooi.config.ConfigFactory;
import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.config.MtgDataSource;
import com.ksptooi.utils.DirectoryTools;
import java.io.File;

public class Application {

    public static void main(String[] args) {

        String currentDir = System.getProperty("user.dir");
        File template = DirectoryTools.findTemplate(currentDir, "velocity");
        assert template!=null;

        MtgDataSource ds = ConfigFactory.datasource()
                .driver("com.mysql.cj.jdbc.Driver")
                .host("127.0.0.1:3306")
                .username("root")
                .password("root")
                .params("?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8")
                .dbName("skinserver")
                .templatePath(template.getAbsolutePath())
                .build();

        MtgGenOptions opt = ConfigFactory.config()
                .genController(true)
                .genService(true)
                .withImpl(false)
                .genPo(true)
                .genMapper(true)
                .packetName("com.ksptooi.app")
                .tableName("oauth_clients")
                //.projectName("projectName")
                .enableLombok(true)
                .enableSwagger2(true)
                .enableMybatisPlus(true)
                .silence(true)
                .namespace("ns_private")
                .put("private_ns_test","HelloWorld")
                .build();

        MtGenerator mtg = new MtGenerator(ds,opt);
        mtg.generate();
        //mtg.generate();
    }


}
