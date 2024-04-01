package com.ksptooi.config;

import com.ksptooi.model.config.MtgGenOptions;

public class GenConfigBuilder {

    private MtgGenOptions target = null;


    public GenConfigBuilder(){
        target = new MtgGenOptions();
    }


    public GenConfigBuilder genController(boolean b){
        target.setGenController(b);
        return this;
    }

    public GenConfigBuilder genService(boolean b){
        target.setGenPo(b);
        return this;
    }

    public GenConfigBuilder withImpl(boolean b){
        target.setWithImpl(b);
        return this;
    }

    public GenConfigBuilder genPo(boolean b){
        target.setGenPo(b);
        return this;
    }

    public GenConfigBuilder genMapper(boolean b){
        target.setGenMapper(b);
        return this;
    }

    public GenConfigBuilder packetName(String in){
        target.setPacketName(in);
        return this;
    }

    public GenConfigBuilder controllerName(String in){
        target.setControllerName(in);
        return this;
    }

    public GenConfigBuilder serviceName(String in){
        target.setServiceName(in);
        return this;
    }

    public GenConfigBuilder serviceImplName(String in){
        target.setServiceImplName(in);
        return this;
    }

    public GenConfigBuilder poName(String in){
        target.setPoName(in);
        return this;
    }

    public GenConfigBuilder mapperName(String in){
        target.setMapperName(in);
        return this;
    }


    public MtgGenOptions build(){
        return target;
    }

}
