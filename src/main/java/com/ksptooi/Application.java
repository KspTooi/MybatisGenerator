package com.ksptooi;

import com.ksptooi.model.MtgConfig;
import com.ksptooi.model.MtgDataSource;

import java.io.File;

public class Application {

    public static void main(String[] args) {

        MtgDataSource ds = new MtgDataSource();
        ds.driverName("com.mysql.cj.jdbc.Driver")
                .dbHost("127.0.0.1:3306")
                .dbName("databrain")
                .dbUserName("root")
                .dbPassword("root")
                .params("?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8")
                .templatePath("C:\\InternalDev\\KspTooiProject\\MysqlTableGenerator\\src\\main\\resources\\freemarker\\");

        MtgConfig c = new MtgConfig();
        c.setGenMapper(true);
        c.setGenPo(true);

        c.setPacketName("com.ksptooi.app");
        c.setPacketNameMapper("com.ksptooi.app.mapper");
        c.setPacketNamePo("com.ksptooi.app.model.po");

        c.setPrimaryField("sid");
        c.setMapperName("ZskTagRuleMapper");
        c.setPoName("ZskTagRulePo");
        c.setTableName("zsk_tag_rule");

        c.setOutputPath(new File("C://Output/"));

        MtGenerator mtg = new MtGenerator(ds,c);
        mtg.generate();
    }

}
