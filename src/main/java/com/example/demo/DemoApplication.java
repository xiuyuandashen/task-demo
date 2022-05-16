package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@MapperScan("com.example.demo.mapper")
public class DemoApplication {

	@GetMapping("/")
	String home() {
		return "Spring is here!";
	}

	@GetMapping("/hello")
	String hello(){
		return "hello world";
	}

	@GetMapping("/test")
	String JenkinsTest(){
		return "success!";
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}