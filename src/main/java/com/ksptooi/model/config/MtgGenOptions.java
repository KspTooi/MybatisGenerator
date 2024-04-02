package com.ksptooi.model.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MtgGenOptions {

    private final Map<String, Object> opt = new HashMap<>();

    /**
     * 生成器配置
     */
    private boolean genController = true;
    private boolean genService = true;
    private boolean withImpl = true;
    private boolean genPo = true;
    private boolean genVo = true;
    private boolean genMapper = true;

    /**
     * 名称配置
     */
    private String packetName;
    private String controllerName;
    private String serviceName;
    private String serviceImplName;
    private String poName;
    private String voName;
    private String mapperName;

    /**
     * 包名称配置
     */
    private String pkgNameController;

    private String pkgNameService;

    private String pkgNameServiceImpl;

    private String pkgNamePo;

    private String pkgNameVo;

    private String pkgNameMapper;

    private String pkgNameMapperXml;

    /**
     * 通用配置
     */
    private String tableName;
    private String primaryField;
    private String projectName;
    private String templatePath; //模板文件路径
    private String templateNameSpace;  //模板文件的命名空间
    private boolean silence; //为true 不打印输出文件日志

    /**
     * 组件配置
     */
    private boolean enableLombok = false;
    private boolean enableSlf4J = false;
    private boolean enableSwagger2 = false;
    private boolean enableMybatisPlus = false;

    /**
     * 输出配置
     * @return
     */
    private File outputPath;
    private File outputXmlPath;


    public File getOutputXmlPath() {
        return outputXmlPath;
    }

    public void setOutputXmlPath(File outputXmlPath) {
        this.outputXmlPath = outputXmlPath;
    }

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

    public String getPoName() {
        return poName;
    }

    public void setPoName(String poName) {
        this.poName = poName;
    }

    public String getMapperName() {
        return mapperName;
    }

    public void setMapperName(String mapperName) {
        this.mapperName = mapperName;
    }

    public String getPkgNameController() {
        return pkgNameController;
    }

    public void setPkgNameController(String pkgNameController) {
        this.pkgNameController = pkgNameController;
    }

    public String getPkgNameService() {
        return pkgNameService;
    }

    public void setPkgNameService(String pkgNameService) {
        this.pkgNameService = pkgNameService;
    }

    public String getPkgNameServiceImpl() {
        return pkgNameServiceImpl;
    }

    public void setPkgNameServiceImpl(String pkgNameServiceImpl) {
        this.pkgNameServiceImpl = pkgNameServiceImpl;
    }

    public String getPkgNamePo() {
        return pkgNamePo;
    }

    public void setPkgNamePo(String pkgNamePo) {
        this.pkgNamePo = pkgNamePo;
    }

    public String getPkgNameMapper() {
        return pkgNameMapper;
    }

    public void setPkgNameMapper(String pkgNameMapper) {
        this.pkgNameMapper = pkgNameMapper;
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

    public File getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(File outputPath) {
        this.outputPath = outputPath;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public boolean isEnableLombok() {
        return enableLombok;
    }

    public void setEnableLombok(boolean enableLombok) {
        this.enableLombok = enableLombok;
    }

    public boolean isEnableSlf4J() {
        return enableSlf4J;
    }

    public void setEnableSlf4J(boolean enableSlf4J) {
        this.enableSlf4J = enableSlf4J;
    }

    public boolean isEnableSwagger2() {
        return enableSwagger2;
    }

    public void setEnableSwagger2(boolean enableSwagger2) {
        this.enableSwagger2 = enableSwagger2;
    }

    public boolean isGenVo() {
        return genVo;
    }

    public void setGenVo(boolean genVo) {
        this.genVo = genVo;
    }

    public String getVoName() {
        return voName;
    }

    public void setVoName(String voName) {
        this.voName = voName;
    }

    public String getPkgNameVo() {
        return pkgNameVo;
    }

    public void setPkgNameVo(String pkgNameVo) {
        this.pkgNameVo = pkgNameVo;
    }

    public String getPkgNameMapperXml() {
        return pkgNameMapperXml;
    }

    public void setPkgNameMapperXml(String pkgNameMapperXml) {
        this.pkgNameMapperXml = pkgNameMapperXml;
    }

    public boolean isEnableMybatisPlus() {
        return enableMybatisPlus;
    }

    public void setEnableMybatisPlus(boolean enableMybatisPlus) {
        this.enableMybatisPlus = enableMybatisPlus;
    }

    public String getTemplateNameSpace() {
        return templateNameSpace;
    }

    public void setTemplateNameSpace(String templateNameSpace) {
        this.templateNameSpace = templateNameSpace;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    public boolean isSilence() {
        return silence;
    }

    public void setSilence(boolean silence) {
        this.silence = silence;
    }

    public Map<String, Object> getOpt() {
        return opt;
    }
}
