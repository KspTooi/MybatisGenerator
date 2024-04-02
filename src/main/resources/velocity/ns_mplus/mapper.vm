package ${pkgNameMapper};

import ${pkgNamePo}.${poName};
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface ${mapperName} extends BaseMapper<${poName}>{

    #foreach ($field in $fields)
    #if(${field.primary})
    int removeBy${field.javaGetterName}(@Param("val")${field.javaType} val);

    ${poName} getBy${field.javaGetterName}(@Param("val")${field.javaType} val);

    int updateBy${field.javaGetterName}(@Param("val")${poName} val);
    #end
    #end
    
}