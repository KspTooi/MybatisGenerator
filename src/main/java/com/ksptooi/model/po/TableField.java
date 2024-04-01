package com.ksptooi.model.po;

import java.util.List;

public class TableField {

    private String name;

    private String javaFieldName;

    private String javaGetterName;

    private String type;

    private String javaType;

    private String comment;

    private boolean primary;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getJavaFieldName() {
        return javaFieldName;
    }

    public void setJavaFieldName(String javaFieldName) {
        this.javaFieldName = javaFieldName;
    }

    public String getJavaGetterName() {
        return javaGetterName;
    }

    public void setJavaGetterName(String javaGetterName) {
        this.javaGetterName = javaGetterName;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public static TableField getPrimary(List<TableField> fields){

        for(TableField item : fields){
            if(item.isPrimary()){
                return item;
            }
        }

        return null;
    }
}
