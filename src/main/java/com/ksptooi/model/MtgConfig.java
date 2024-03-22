package com.ksptooi.model;

import java.io.File;

public class MtgConfig {

    private boolean genMapper = true;
    private boolean genPo = true;
    private String packetName;
    private String mapperName;
    private String poName;
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
}
