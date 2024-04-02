package com.ksptooi.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class DirectoryTools {

    /**
     * 遍历找到模板文件夹
     */
    public static File findTemplate(String path,String templateDirName){

        List<File> freemarkerDirs = new ArrayList<>();
        Path startPath = Paths.get(path);

        try {
            Files.walkFileTree(startPath, new SimpleFileVisitor<>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    if (dir.getFileName().toString().equals(templateDirName)) {
                        freemarkerDirs.add(dir.toFile());
                        return FileVisitResult.SKIP_SUBTREE; // Skip processing this subtree
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the IOException
        }

        if(freemarkerDirs.isEmpty()){
            return null;
        }

        return freemarkerDirs.get(0);
    }

    public static void main(String[] args) {
        File template = findTemplate("C:\\InternalDev\\NanliProject\\databrain-backend\\","freemarker");
        System.out.println(template.getAbsolutePath());
    }

}
