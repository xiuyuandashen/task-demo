package com.example.demo.Task;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class parameterTask implements Runnable{

    private String name;


    @Override
    public void run() {
      log.info("name: [{}]",name);
    }
}
