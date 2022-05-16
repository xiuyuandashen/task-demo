package com.example.demo.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class HelloTask2 implements Runnable{
private static Logger log  = LoggerFactory.getLogger(HelloTask2.class);
@Override
public void run() {
log.info("hello world task");
}
}