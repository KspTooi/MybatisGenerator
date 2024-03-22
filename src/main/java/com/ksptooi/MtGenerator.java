package com.ksptooi;

import com.ksptooi.app.DatabaseTools;
import com.ksptooi.app.VelocityWrapper;
import com.ksptooi.model.MtgConfig;
import com.ksptooi.model.MtgDataSource;
import com.ksptooi.model.TableField;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.StringWriter;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

public class MtGenerator {

    private final static Logger log = LoggerFactory.getLogger(MtGenerator.class);

    private final MtgDataSource dataSource;
    private MtgConfig config;
    private Connection dsConn;

    private DatabaseTools dbt;

    public MtGenerator(MtgDataSource ds, MtgConfig config){
        this.dataSource = ds;
        this.config = config;
        initDataSource();
    }

    // Initialization method for DataSource
    private void initDataSource(){
        try{

            File tPath = new File(dataSource.getTemplatePath());

            if(!tPath.exists() || !tPath.isDirectory()){
                log.error("VE初始化失败 模板路径无效:{}",tPath);
                return;
            }

            VelocityWrapper.init(dataSource.getTemplatePath());
            log.info("VE初始化成功:{}",tPath);

            Class.forName(dataSource.getDriverName());

            String url = "jdbc:mysql://#{host}/#{database}#{params}";
            url = url.replace("#{host}", dataSource.getDbHost());
            url = url.replace("#{database}", dataSource.getDbName());
            url = url.replace("#{params}", dataSource.getParams());
            dsConn = DriverManager.getConnection(url, dataSource.getDbUserName(), this.dataSource.getDbPassword());

            log.info("数据源初始化成功:{}",url);

            dbt = new DatabaseTools(dsConn);

            List<TableField> zskTagRule = dbt.getFieldsByTable("zsk_tag_rule");

            System.out.println(zskTagRule);


        }catch(Exception e){
            // Handle errors for Class.forName
            e.printStackTrace();
        }
    }


    public void generate() {

        try {

            if (dsConn == null || dsConn.isClosed()) {
                log.error("执行生成时出现错误,数据源未初始化.");
                return;
            }

            String tableName = config.getTableName();
            DatabaseMetaData dbm = dsConn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, tableName, null);
            if (!tables.next()) {
                log.error("执行生成时出现错误 指定的表不存在:{}", tableName);
                return;
            }

            if(config.isGenMapper()){
                generateMapper();
            }

            if(config.isGenPo()){
                generatePo();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void generateMapper(){

    }

    private void generatePo(){

        final VelocityContext vc = new VelocityContext();
        vc.put("packetNamePo",config.getPacketNamePo());
        vc.put("poName",config.getPoName());

        File out = new File(config.getOutputPath(),config.getPoName() + ".java");
        Template t = VelocityWrapper.getTemplate("po.ftl");

        List<TableField> fieldsByTable = dbt.getFieldsByTable(config.getTableName());
        vc.put("fields",fieldsByTable);

        VelocityWrapper.mergeAndOutput(t,vc,out);
    }





}