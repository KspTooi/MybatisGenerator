package com.ksptooi.autoconfig;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class PacketNameAutoConfigurator implements AutoConfigurator{

    private final Logger log = LoggerFactory.getLogger(PacketNameAutoConfigurator.class);

    @Override
    public void doAutomaticConfiguration(Connection conn, MtgGenOptions opt, List<TableField> fields) {

        assert fields != null && !fields.isEmpty();
        assert !StringUtils.isBlank(opt.getPacketName());
        assert !StringUtils.isBlank(opt.getTableName());

        String pkgName = opt.getPacketName();

        //pkgName以.结尾
        if(pkgName.endsWith(".")){
            pkgName = pkgName.substring(0,pkgName.length() - 1);
            log.info("重命名PacketName为: {}",pkgName);
        }

        if(StringUtils.isBlank(opt.getControllerName())){
            String pkgNameCtl = pkgName + ".controller";
            opt.setPkgNameController(pkgNameCtl);
            log.info("自动配置PkgNameController为: {}",pkgNameCtl);
        }

        if(StringUtils.isBlank(opt.getPkgNameService())){
            String pkgNameService = pkgName + ".services";
            opt.setPkgNameService(pkgNameService);
            log.info("自动配置PkgNameService为: {}",pkgNameService);
        }

        if(StringUtils.isBlank(opt.getPkgNameServiceImpl())){

            String pkgNameServiceImpl = pkgName + ".services";

            //开启impl
            if(opt.isWithImpl()){
                pkgNameServiceImpl = pkgName + ".services.impl";
            }

            opt.setPkgNameServiceImpl(pkgNameServiceImpl);
            log.info("自动配置PkgNameServiceImpl为: {}",pkgNameServiceImpl);
        }

        if(StringUtils.isBlank(opt.getPkgNamePo())){
            String pkgNamePo = pkgName + ".model.po";
            opt.setPkgNameServiceImpl(pkgNamePo);
            log.info("自动配置PkgNamePo为: {}",pkgNamePo);
        }

        if(StringUtils.isBlank(opt.getPkgNameMapper())){
            String pkgNameMapper = pkgName + ".mapper";
            opt.setPkgNameMapper(pkgNameMapper);
            log.info("自动配置PkgNameMapper为: {}",pkgNameMapper);
        }

    }


    @Override
    public String getName() {
        return "包名称自动配置器";
    }
}
