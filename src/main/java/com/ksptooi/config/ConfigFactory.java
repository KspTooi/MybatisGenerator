package com.ksptooi.config;

public class ConfigFactory {

    public static DsConfigBuilder datasource(){
        return new DsConfigBuilder();
    }

    public static GenConfigBuilder config(){
        return new GenConfigBuilder();
    }

}
