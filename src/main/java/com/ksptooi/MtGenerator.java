package com.ksptooi;

import com.ksptooi.model.MtgConfig;
import com.ksptooi.model.MtgDataSource;

public class MtGenerator {

    private MtgDataSource dataSource;

    private MtgConfig config;

    public MtGenerator(MtgDataSource ds, MtgConfig config){
        this.dataSource = ds;
        this.config = config;
    }


    //初始化数据源
    private void initDataSource(){

    }

}
