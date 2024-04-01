package com.ksptooi.autoconfig;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

public class PacketNameAutoConfigurator implements AutoConfigurator {

    private Logger log = LoggerFactory.getLogger(PacketNameAutoConfigurator.class);

    @Override
    public void doAutomaticConfiguration(Connection conn, MtgGenOptions opt, List<TableField> fields) {

        assert fields != null && !fields.isEmpty();
        assert !StringUtils.isBlank(opt.getPacketName());
        assert !StringUtils.isBlank(opt.getTableName());

        final String clazzName = opt.getTableName();


    }

    @Override
    public String getName() {
        return "包名称自动配置器";
    }

}
