package com.ksptooi.app.mapper;

import com.ksptooi.app.model.po.OauthClientsPo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

@Mapper
public interface OauthClientsMapper extends BaseMapper<OauthClientsPo>{

    int removeById(@Param("val")Long val);

    OauthClientsPo getById(@Param("val")Long val);

    int updateById(@Param("val")OauthClientsPo val);
    
}