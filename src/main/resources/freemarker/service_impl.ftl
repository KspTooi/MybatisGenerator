package ${pkgNameServiceImpl};

import ${pkgNamePo}.*
import ${pkgNameVo}.*

@Service
public class ${serviceImplName}{

    @Autowired
    private ${serviceImplName} ${fieldMapperName};

    @Autowired
    private Snowflake idWorker;

    @Override
    public TableDataInfo<${poName}> getMany(${poName} vo){
        PageHelper.startPage(vo.getPageNum(),vo.getPageSize());
        //PageHelper.orderBy("create_time desc");
        List<${poName}> vos = ${fieldMapperName}.getMany(vo);
        return BaseController.getDataTable(vos);
    }

    @Override
    public String saveOrUpdate(${voName}InVo in) {

        final ${poName} po = new ${poName}();

        #foreach ($field in $fields)
        po.set${field.javaGetterName}(in.get${field.javaGetterName});
        #end

        return ${fieldMapperName}.insertOrUpdate()+"";
    }

    #foreach ($field in $fields)
    #if(${field.primary})
    @Override
    public ${poName} getBy${field.javaGetterName}(${field.javaType} ${field.javaFieldName}) {
        return ${fieldMapperName}.getBy${field.javaGetterName}(${field.javaFieldName});
    }

    @Override
    public int removeBy${field.javaGetterName}(${field.javaType} ${field.javaFieldName}) {
        return ${fieldMapperName}.removeBy${field.javaGetterName}(${field.javaFieldName})
    }
    #end
    #end



}