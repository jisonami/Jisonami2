package org.jisonami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class ApplicationMain {

	@RequestMapping("/")
	public String hello(){
		return "hello";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ApplicationMain.class, args);
	}
}
