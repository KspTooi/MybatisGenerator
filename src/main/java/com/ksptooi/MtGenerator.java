package com.ksptooi;

import com.ksptooi.autoconfig.*;
import com.ksptooi.generator.*;
import com.ksptooi.utils.DatabaseTools;
import com.ksptooi.utils.VelocityWrapper;
import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.config.MtgDataSource;
import com.ksptooi.model.po.TableField;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MtGenerator {

    private final static Logger log = LoggerFactory.getLogger(MtGenerator.class);

    private final MtgDataSource dataSource;
    private MtgGenOptions config;
    private Connection dsConn;
    private DatabaseTools dbt;

    //生成器链
    private List<Generator> generators = new ArrayList<>();

    //OPT自动配置链
    private List<AutoConfigurator> autoConfigurators = new ArrayList<>();




    public void addAutoConfigurator(AutoConfigurator auto){
        autoConfigurators.add(auto);
    }

    public MtGenerator(MtgDataSource ds, MtgGenOptions config){
        this.dataSource = ds;
        this.config = config;
        initDataSource();
    }

    /**
     * 初始化默认的自动配置类 提供约定俗成的默认配置
     */
    private void initDefaultAutoConfig(){
        if(autoConfigurators.isEmpty()){
            autoConfigurators.add(new EntityNameAutoConfigurator());
            autoConfigurators.add(new PacketNameAutoConfigurator());
            autoConfigurators.add(new PrimaryKeyAutoConfigurator());
            autoConfigurators.add(new PathsAutoConfigurator());
        }
    }

    /**
     * 初始化默认的自动生成类 提供约定俗成的默认生成模板
     */
    private void initDefaultGenerators(){
        if(generators.isEmpty()){
            generators.add(new GeneratorController());
            generators.add(new GeneratorServices());
            generators.add(new GeneratorMapper());
            generators.add(new GeneratorEntities());
        }
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

            List<TableField> fields = dbt.getFieldsByTable(config.getTableName());

            initDefaultAutoConfig();
            initDefaultGenerators();

            for (AutoConfigurator item : autoConfigurators){
                item.doAutomaticConfiguration(dsConn,config,fields);
            }

            if(config.isWithImpl()){
                throw new RuntimeException("当前不支持withImpl参数");
            }

            for(Generator item : generators){
                item.generate(config,fields);
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}