package com.ksptooi.model;

import java.io.File;

public class MtgDataSource {

    private boolean genMapper = true;

    private boolean genPo = true;

    private String driverName;

    private String dsUrl;

    private String dbName;

    private String dsUserName;

    private String dsPassword;


    public MtgDataSource genMapper(boolean i){
        genMapper = i;
        return this;
    }

    public MtgDataSource genPo(boolean i){
        genPo = i;
        return this;
    }

    public MtgDataSource driverName(String i){
        driverName = i;
        return this;
    }

    public MtgDataSource dsUrl(String i){
        dsUrl = i;
        return this;
    }

    public MtgDataSource dbName(String i){
        dbName = i;
        return this;
    }

    public MtgDataSource dsUserName(String i){
        dsUserName = i;
        return this;
    }

    public MtgDataSource dsPassword(String i){
        dsPassword = i;
        return this;
    }

    public MtgDataSource output(File i){
        output = i;
        return this;
    }

    public MtgDataSource packetName(String i){
        packetName = i;
        return this;
    }

}
