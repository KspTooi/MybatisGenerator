package com.ksptooi;

import com.ksptooi.config.ConfigFactory;
import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.config.MtgDataSource;
import com.ksptooi.utils.DirectoryTools;
import java.io.File;

public class Application {

    public static void main(String[] args) {

        String currentDir = System.getProperty("user.dir");
        File template = DirectoryTools.findTemplate(currentDir, "freemarker");
        assert template!=null;

        MtgDataSource ds = ConfigFactory.datasource()
                .driver("com.mysql.cj.jdbc.Driver")
                .host("192.168.10.200:3306")
                .username("root")
                .password("root")
                .params("?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8")
                .dbName("databrain")
                .templatePath(template.getAbsolutePath())
                .build();

        MtgGenOptions opt = ConfigFactory.config()
                .genController(true)
                .genService(true)
                .withImpl(false)
                .genPo(true)
                .genMapper(true)
                .packetName("com.ksptooi.app")
                .tableName("ai_model_info")
                .projectName("zsk-basic-service")
                .enableLombok(true)
                .enableSwagger2(true)
                .enableMybatisPlus(true)
                .enableSlf4J(true)
                .build();

        MtGenerator mtg = new MtGenerator(ds,opt);
        mtg.generate();
        //mtg.generate();
    }


}
