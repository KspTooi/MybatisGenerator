package com.ksptooi.model;

import java.io.File;

public class MtgDataSource {

    private String driverName;

    private String dbHost;

    private String dbName;

    private String dbUserName;

    private String dbPassword;

    private String params;


    public MtgDataSource driverName(String i){
        driverName = i;
        return this;
    }

    public MtgDataSource params(String i){
        params = i;
        return this;
    }

    public MtgDataSource dbHost(String i){
        dbHost = i;
        return this;
    }

    public MtgDataSource dbName(String i){
        dbName = i;
        return this;
    }

    public MtgDataSource dbUserName(String i){
        dbUserName = i;
        return this;
    }

    public MtgDataSource dbPassword(String i){
        dbPassword = i;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getParams() {
        return params;
    }
}
