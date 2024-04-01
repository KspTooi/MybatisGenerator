package com.ksptooi.autoconfig;

import com.ksptooi.model.config.MtgGenOptions;
import com.ksptooi.model.po.TableField;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrimaryKeyAutoConfigurator implements AutoConfigurator{

    private final Logger log = LoggerFactory.getLogger(PacketNameAutoConfigurator.class);

    @Override
    public void doAutomaticConfiguration(Connection conn, MtgGenOptions opt, List<TableField> fields) {

        if(StringUtils.isNotBlank(opt.getPrimaryField())){
            log.info("主键已手动配置为:{}",opt.getPrimaryField());
            setPkFlag(opt.getPrimaryField(),fields);
            return;
        }

        try {
            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet rs = metaData.getPrimaryKeys(null, null, opt.getTableName());

            List<String> pks = new ArrayList<>();

            while(rs.next()){
                pks.add(rs.getString("COLUMN_NAME"));
            }

            if(pks.isEmpty()){
                log.info("自动配置失败 当前表 {} 不含有主键 ",opt.getTableName());
                opt.setPrimaryField(null);
                return;
            }

            if(!pks.isEmpty()){
                log.info("[自动配置]当前表 {} 共有 {} 个主键",opt.getTableName(),pks.size());
                String pk = pks.get(0);
                log.info("[自动配置]选择 {} 作为生成主键",pk);
                opt.setPrimaryField(pk);
                setPkFlag(pk,fields);
            }

            rs.close();
        } catch (SQLException e) {
            System.err.println("Unable to retrieve primary keys. Reason: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void setPkFlag(String field,List<TableField> fields){


        for(TableField item : fields){
            if(item.getName().equals(field)){
                item.setPrimary(true);
                log.info("[自动配置]已成功设置主键 位于:{}",item.getName());
                return;
            }
        }

        log.error("自动配置失败 无法在TableField中配置主键 位于:{}",field);
        throw new RuntimeException("自动配置失败 无法在TableField中配置主键");
    }

    @Override
    public String getName() {
        return "自动主键配置器";
    }
}
