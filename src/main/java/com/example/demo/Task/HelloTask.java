package com.example.demo.Task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloTask implements Runnable{
    @Override
    public void run() {
      log.info("hello world task");
    }
}
