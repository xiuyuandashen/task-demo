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
   
   
   public static Logger logger = LoggerFactory.getLogger(classGenerate.class);
   
   /**
     * 生成java文件并加载class
     *
     * @param path     /com/example/demo/xxx/xxx.java 类生成路径
     * @param JavaCode 类代码 根据 package 生成class文件的位置！
     * @throws Exception
     */
    public static void createStudentByFile(String path, String JavaCode) throws Exception {
//        String fileName = System.getProperty("user.dir") + "/src/main/java"+path;
//        String fileName = "E:/data/JavaCode"+path;
//        String fileName = "/data/JavaCode"+path;
        // path = com/xx/xxx/task/xxx.java 就是Java代码生成的目录

        String fileName = path;
        logger.info("fileName:[{}]", fileName);
        File file = new File(fileName);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
        }
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(JavaCode);
        fileWriter.flush();
        fileWriter.close();
        // xxxx/classes/
        URL location = DemoApplication.class.getProtectionDomain().getCodeSource().getLocation();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> javaFileObjects = manager.getJavaFileObjects(fileName);

        String dest = location.getPath();
        logger.info("dest: [ {} ]", dest);
        //options就是指定编译输入目录，与我们命令行写javac -d C://是一样的
        List<String> options = new ArrayList<String>();
        options.add("-d");
        options.add(dest);
        JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null, options, null, javaFileObjects);
        task.call();
        manager.close();
//        URL[] urls = new URL[]{new URL("file:/" + System.getProperty("user.dir") + "/target/classes")};

        //加载class时要告诉你的classloader去哪个位置加载class文件

//        ClassLoader classLoader = new URLClassLoader(urls);
//        Object student = classLoader.loadClass("com.example.demo.Task.HelloTask2").newInstance();
    }
}
