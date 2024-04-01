package com.ksptooi.app.mapper;

import com.ksptooi.app.model.po.JobsPo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JobsMapper{

    int insert(@Param("val")JobsPo val);

    int insertOrUpdate(@Param("val")JobsPo val);

    int insertList(@Param("data")List<JobsPo> data);

    JobsPo getOne(@Param("val")JobsPo val);

    List<JobsPo> getMany(@Param("val")JobsPo val);

    int removeById(@Param("val")Long val);

    JobsPo getById(@Param("val")Long val);

    int updateById(@Param("val")JobsPo val);
    
}