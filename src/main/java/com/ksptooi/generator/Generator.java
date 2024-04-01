package com.ksptooi.generator;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;

public interface Generator {

    public boolean enable(MtgGenOptions opt);

    public void generate(MtgGenOptions opt, TableField field);

    public String getName();
}
