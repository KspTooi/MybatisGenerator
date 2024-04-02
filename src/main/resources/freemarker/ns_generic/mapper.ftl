package ${pkgNameMapper};

import ${pkgNamePo}.${poName};
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ${mapperName}{

    int insert(@Param("val")${poName} val);

    int insertOrUpdate(@Param("val")${poName} val);

    int insertList(@Param("data")List<${poName}> data);

    ${poName} getOne(@Param("val")${poName} val);

    List<${poName}> getMany(@Param("val")${poName} val);

    #foreach ($field in $fields)
    #if(${field.primary})
    int removeBy${field.javaGetterName}(@Param("val")${field.javaType} val);

    ${poName} getBy${field.javaGetterName}(@Param("val")${field.javaType} val);

    int updateBy${field.javaGetterName}(@Param("val")${poName} val);
    #end
    #end
    
}