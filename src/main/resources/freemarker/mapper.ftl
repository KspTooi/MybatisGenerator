package ${packetNameMapper};

import ${packetNamePo}.${poName};
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ${mapperName}{

    int insert(@Param("val")${poName} val);

    int insertOrUpdate(@Param("val")${poName} val);

    int insertList(@Param("data")List<${poName}> data);

    int removeBy(@Param("val")Long val);

    ${poName} getBy(@Param("val")Long val);

    int updateBy(@Param("val")${poName} val);

    ${poName} getOne(@Param("val")${poName} val);

    List<${poName}> getMany(@Param("val")${poName} val);
    
}