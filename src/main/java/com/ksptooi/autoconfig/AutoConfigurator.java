package com.ksptooi.autoconfig;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;

import java.sql.Connection;
import java.util.List;

public interface AutoConfigurator {

    public void doAutomaticConfiguration(Connection conn, MtgGenOptions opt, List<TableField> fields);

    public String getName();

}
