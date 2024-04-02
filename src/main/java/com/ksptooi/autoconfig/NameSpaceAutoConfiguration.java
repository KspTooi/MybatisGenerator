package com.ksptooi.autoconfig;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import com.ksptooi.utils.VelocityWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.util.List;

public class NameSpaceAutoConfiguration implements AutoConfigurator{

    private static final Logger log = LoggerFactory.getLogger(NameSpaceAutoConfiguration.class);

    @Override
    public void doAutomaticConfiguration(Connection conn, MtgGenOptions opt, List<TableField> fields) {

        String ns = "ns_generic";

        if(StringUtils.isBlank(opt.getTemplateNameSpace())){
            opt.setTemplateNameSpace(ns);
            VelocityWrapper.setNamespace(ns);
        }

        if(opt.isEnableMybatisPlus()){
            ns = "ns_mplus";
            opt.setTemplateNameSpace(ns);
            VelocityWrapper.setNamespace(ns);
        }

        log.info("[自动配置]模板命名空间为:{}",ns);
    }

    @Override
    public String getName() {
        return "模板命名空间自动配置";
    }
}
