package com.ksptooi;

import com.ksptooi.config.ConfigFactory;
import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.config.MtgDataSource;

import java.io.File;

public class Application {

    public static void main(String[] args) {

        MtgDataSource ds = ConfigFactory.datasource()
                .driver("com.mysql.cj.jdbc.Driver")
                .dbName("databrain")
                .username("root")
                .password("root")
                .params("?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8")
                .templatePath("C:\\InternalDev\\KspTooiProject\\MysqlTableGenerator\\src\\main\\resources\\freemarker\\")
                .build();


        MtgGenOptions c = new MtgGenOptions();
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
