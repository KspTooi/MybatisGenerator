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
            log.info("[自动配置]重命名PacketName为: {}",pkgName);
            opt.setPacketName(pkgName);
        }

        if(StringUtils.isBlank(opt.getPkgNameController())){
            String pkgNameCtl = pkgName + ".controller";
            log.info("[自动配置]PkgNameController为: {}",pkgNameCtl);
            opt.setPkgNameController(pkgNameCtl);
        }

        if(StringUtils.isBlank(opt.getPkgNameService())){
            String pkgNameService = pkgName + ".services";
            log.info("[自动配置]PkgNameService为: {}",pkgNameService);
            opt.setPkgNameService(pkgNameService);
        }

        if(StringUtils.isBlank(opt.getPkgNameServiceImpl())){

            String pkgNameServiceImpl = pkgName + ".services";

            //开启impl
            if(opt.isWithImpl()){
                pkgNameServiceImpl = pkgName + ".services.impl";
            }

            log.info("[自动配置]PkgNameServiceImpl为: {}",pkgNameServiceImpl);
            opt.setPkgNameServiceImpl(pkgNameServiceImpl);
        }

        if(StringUtils.isBlank(opt.getPkgNamePo())){
            String pkgNamePo = pkgName + ".model.po";
            log.info("[自动配置]PkgNamePo为: {}",pkgNamePo);
            opt.setPkgNamePo(pkgNamePo);
        }

        if(StringUtils.isBlank(opt.getPkgNameMapper())){
            String pkgNameMapper = pkgName + ".mapper";
            log.info("[自动配置]PkgNameMapper为: {}",pkgNameMapper);
            opt.setPkgNameMapper(pkgNameMapper);
        }

    }


    @Override
    public String getName() {
        return "包名称自动配置器";
    }
}
