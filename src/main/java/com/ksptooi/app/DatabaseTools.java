package com.ksptooi.app;

import com.ksptooi.model.po.TableField;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseTools {

    private final Connection conn;


    //类型映射表
    private final HashMap<String,String> typeMapper = new HashMap<>();

    public DatabaseTools(Connection conn){
        this.conn = conn;
        initTypeMapper();
    }

    private void initTypeMapper(){
        // MySQL to Java Type Mapping
        typeMapper.put("CHAR", "String");
        typeMapper.put("VARCHAR", "String");
        typeMapper.put("LONGVARCHAR", "String");
        typeMapper.put("BIT", "Boolean");
        typeMapper.put("NUMERIC", "java.math.BigDecimal");
        typeMapper.put("TINYINT", "Byte");
        typeMapper.put("SMALLINT", "Short");
        typeMapper.put("INTEGER", "Integer");
        typeMapper.put("BIGINT", "Long");
        typeMapper.put("REAL", "Float");
        typeMapper.put("FLOAT", "Double");
        typeMapper.put("DOUBLE", "Double");
        typeMapper.put("BINARY", "Byte[]");
        typeMapper.put("VARBINARY", "Byte[]");
        typeMapper.put("LONGVARBINARY", "Byte[]");
        typeMapper.put("DATE", "Date");
        typeMapper.put("TIME", "Date");
        typeMapper.put("TIMESTAMP", "java.sql.Timestamp");
    }

    public List<TableField> getFieldsByTable(String tableName){

        List<TableField> tableFields = new ArrayList<>();
        try {
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            ResultSet resultSet = databaseMetaData.getColumns(null, null, tableName, null);
            while(resultSet.next()){

                String name = resultSet.getString("COLUMN_NAME");
                String type = resultSet.getString("TYPE_NAME");
                String comment = resultSet.getString("REMARKS");

                TableField tableField = new TableField();
                tableField.setName(name);
                tableField.setJavaFieldName(toJavaName(name));
                tableField.setJavaGetterName(toJavaGetterName(toJavaName(name)));
                tableField.setType(type);
                tableField.setJavaType(typeMapper.get(type));
                tableField.setComment(comment);
                if(comment == null || comment.isBlank()){
                    tableField.setComment("");
                }
                tableFields.add(tableField);
            }
            resultSet.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return tableFields;

    }


    private String toJavaGetterName(String fieldName){
        char[] chars = fieldName.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    private String toJavaName(String fieldName){
        String[] words = fieldName.split("_");
        StringBuilder javaName = new StringBuilder(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            javaName.append(Character.toUpperCase(words[i].charAt(0)))
                    .append(words[i].substring(1).toLowerCase());
        }
        return javaName.toString();
    }

}
