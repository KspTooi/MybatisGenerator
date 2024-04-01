package com.ksptooi.generator;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;

import java.util.List;

public class GeneratorMapper implements Generator{

    @Override
    public boolean enable(MtgGenOptions opt) {
        return opt.isGenMapper();
    }

    @Override
    public void generate(MtgGenOptions opt, List<TableField> fields) {

    }

    @Override
    public String getName() {
        return "Mapper类生成器";
    }
}
