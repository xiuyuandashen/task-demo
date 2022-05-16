package com.example.demo.component;

import org.springframework.stereotype.Component;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class生成
 */
public class classGenerate {
    /**
     * 生成java文件并加载class
     * @param path /com/example/demo/xxx/xxx.java 类生成路径
     * @param JavaCode 类代码
     * @throws Exception
     */
    public static void createStudentByFile(String path,String JavaCode) throws Exception{
//        String studentString = "package org.myself.pojo;public class Test{private String studentId;public String getStudentId(){return this.studentId;}public void setStudentId(String studentId){this.studentId = studentId;}}";
        String fileName = System.getProperty("user.dir") + "/src/main/java"+path;
        File file = new File(fileName);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(JavaCode);
        fileWriter.flush();
        fileWriter.close();


        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);
        Iterable<? extends JavaFileObject> javaFileObjects = manager.getJavaFileObjects(fileName);
        String dest = System.getProperty("user.dir") + "/target/classes";

        //options就是指定编译输入目录，与我们命令行写javac -d C://是一样的

        List<String> options = new ArrayList<String>();
        options.add("-d");
        options.add(dest);
        JavaCompiler.CompilationTask task = compiler.getTask(null,manager,null,options,null,javaFileObjects);
        task.call();
        manager.close();
        URL[] urls = new URL[]{new URL("file:/" + System.getProperty("user.dir") + "/target/classes")};

        //加载class时要告诉你的classloader去哪个位置加载class文件

        ClassLoader classLoader = new URLClassLoader(urls);
//        Object student = classLoader.loadClass("com.example.demo.Task.HelloTask2").newInstance();


    }

}
