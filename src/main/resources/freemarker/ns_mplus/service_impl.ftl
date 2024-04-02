package ${pkgNameServiceImpl};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.downgoon.snowflake.Snowflake;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.github.pagehelper.PageHelper;
import java.util.List;
import com.ruoyi.common.core.web.controller.BaseController;
import ${pkgNameVo}.${voName}InVo;
import ${pkgNamePo}.${poName};
import ${pkgNameMapper}.${mapperName};

@Service
public class ${serviceImplName}{

    @Autowired
    private ${mapperName} ${fieldMapperName};

    @Autowired
    private Snowflake idWorker;

    public TableDataInfo getMany(${poName} vo){
        //PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        //PageHelper.orderBy("create_time desc");
        List<${poName}> vos = ${fieldMapperName}.getMany(vo);
        return BaseController.getDataTable(vos);
    }

    public String saveOrUpdate(${voName}InVo in) {

        final ${poName} po = new ${poName}();

        #foreach ($field in $fields)
        po.set${field.javaGetterName}(in.get${field.javaGetterName}());
        #end

        return ${fieldMapperName}.insertOrUpdate(po)+"";
    }

    #foreach ($field in $fields)
    #if(${field.primary})
    public ${poName} getBy${field.javaGetterName}(${field.javaType} ${field.javaFieldName}) {
        return ${fieldMapperName}.getBy${field.javaGetterName}(${field.javaFieldName});
    }

    public int removeBy${field.javaGetterName}(${field.javaType} ${field.javaFieldName}) {
        return ${fieldMapperName}.removeBy${field.javaGetterName}(${field.javaFieldName});
    }
    #end
    #end



}