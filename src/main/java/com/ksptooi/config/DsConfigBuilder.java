package com.ksptooi.config;

import com.ksptooi.model.config.MtgDataSource;

public class DsConfigBuilder {

    private MtgDataSource target = null;

    public DsConfigBuilder(){
        target = new MtgDataSource();
    }

    public DsConfigBuilder driver(String in){
        target.setDriverName(in);
        return this;
    }

    public DsConfigBuilder host(String in){
        target.setDbHost(in);
        return this;
    }

    public DsConfigBuilder dbName(String in){
        target.setDbName(in);
        return this;
    }

    public DsConfigBuilder username(String in){
        target.setDbUserName(in);
        return this;
    }

    public DsConfigBuilder password(String in){
        target.setDbPassword(in);
        return this;
    }

    public DsConfigBuilder params(String in){
        target.setParams(in);
        return this;
    }

    public DsConfigBuilder templatePath(String in){
        target.setTemplatePath(in);
        return this;
    }

    public MtgDataSource build(){
        return target;
    }


}
