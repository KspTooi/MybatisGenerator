package com.ksptooi.generator;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;

import java.util.List;

public interface Generator {

    public boolean enable(MtgGenOptions opt);

    public void generate(MtgGenOptions opt, List<TableField> fields);

    public String getName();
}
