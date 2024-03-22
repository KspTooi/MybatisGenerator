package com.ksptooi.model;

import java.io.File;

public class MtgConfig {

    private boolean genMapper = true;
    private boolean genPo = true;
    private String packetName;
    private String mapperName;
    private String poName;
    private String tableName;
    private String primaryField;
    private String packetNamePo;
    private String packetNameMapper;
    private File outputPath;

    public boolean isGenMapper() {
        return genMapper;
    }

    public void setGenMapper(boolean genMapper) {
        this.genMapper = genMapper;
    }

    public boolean isGenPo() {
        return genPo;
    }

    public void setGenPo(boolean genPo) {
        this.genPo = genPo;
    }

    public String getPacketName() {
        return packetName;
    }

    public void setPacketName(String packetName) {
        this.packetName = packetName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getPoName() {
        return poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public File getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(File outputPath) {
        this.outputPath = outputPath;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryField() {
        return primaryField;
    }

    public void setPrimaryField(String primaryField) {
        this.primaryField = primaryField;
    }

    public String getPacketNamePo() {
        return packetNamePo;
    }

    public void setPacketNamePo(String packetNamePo) {
        this.packetNamePo = packetNamePo;
    }

    public String getPacketNameMapper() {
        return packetNameMapper;
    }

    public void setPacketNameMapper(String packetNameMapper) {
        this.packetNameMapper = packetNameMapper;
    }
}
