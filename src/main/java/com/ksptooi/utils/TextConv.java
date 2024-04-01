package com.ksptooi.utils;

public class TextConv {

    public static String toJavaGetterName(String fieldName){
        char[] chars = fieldName.toCharArray();
        chars[0] = Character.toUpperCase(chars[0]);
        return new String(chars);
    }

    public static String toJavaFiled(String fieldName){
        String[] words = fieldName.split("_");
        StringBuilder javaName = new StringBuilder(words[0].toLowerCase());
        for (int i = 1; i < words.length; i++) {
            javaName.append(Character.toUpperCase(words[i].charAt(0)))
                    .append(words[i].substring(1).toLowerCase());
        }
        return javaName.toString();
    }

    public static String toJavaClass(String name){
        String[] parts = name.split("_");
        StringBuilder camelCaseName = new StringBuilder();

        for (String part : parts) {
            camelCaseName.append(Character.toUpperCase(part.charAt(0)))
                    .append(part.substring(1).toLowerCase());
        }

        return camelCaseName.toString();
    }


}
