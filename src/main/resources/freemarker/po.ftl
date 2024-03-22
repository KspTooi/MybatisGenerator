package ${packetNamePo};

public class ${poName}{

    #foreach ($field in $fields)
    // ${field.comment}
    private ${field.javaType} ${field.javaFieldName};

    #end

    #foreach ($field in $fields)
    public ${field.javaType} ${field.javaGetterName}() {
        return this.${field.javaFieldName};
    }

    public void ${field.javaGetterName}(${field.javaType} ${field.javaFieldName}) {
        this.${field.javaFieldName} = ${field.javaFieldName};
    }
    #end

}

