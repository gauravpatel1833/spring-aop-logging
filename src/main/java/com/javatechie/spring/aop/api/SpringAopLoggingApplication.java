package com.javatechie.spring.aop.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
//@EnableAspectJAutoProxy(proxyTargetClass = true)
// use this annotation if you are autowiring interfaces for the implementations.
public class SpringAopLoggingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopLoggingApplication.class, args);
	}

}
