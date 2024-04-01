package com.ksptooi.model.config;

import java.io.File;

public class MtgGenOptions {

    /**
     * 生成器配置
     */
    private boolean genController = true;
    private boolean genService = true;
    private boolean withImpl = false;
    private boolean genPo = true;
    private boolean genMapper = true;

    /**
     * 名称配置
     */
    private String packetName;

    private String controllerName;

    private String serviceName;

    private String serviceImplName;

    private String poName;

    private String mapperName;

    /**
     * 通用配置
     */
    private String tableName;
    private String primaryField;
    private String packetNamePo;
    private String packetNameMapper;
    private File outputPath;


    public boolean isGenController() {
        return genController;
    }

    public void setGenController(boolean genController) {
        this.genController = genController;
    }

    public boolean isGenService() {
        return genService;
    }

    public void setGenService(boolean genService) {
        this.genService = genService;
    }

    public boolean isWithImpl() {
        return withImpl;
    }

    public void setWithImpl(boolean withImpl) {
        this.withImpl = withImpl;
    }

    public boolean isGenPo() {
        return genPo;
    }

    public void setGenPo(boolean genPo) {
        this.genPo = genPo;
    }

    public boolean isGenMapper() {
        return genMapper;
    }

    public void setGenMapper(boolean genMapper) {
        this.genMapper = genMapper;
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

    public File getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(File outputPath) {
        this.outputPath = outputPath;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImplName() {
        return serviceImplName;
    }

    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }
}
