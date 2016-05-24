package org.jisonami.jisonami2;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Jisonami2Application extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Jisonami2Application.class);
    }
	
	@Bean
	@ConfigurationProperties(locations="classpath:DBConfig.properties", prefix="datasource")
	public DataSource dataSource(){
		return DataSourceBuilder.create().build();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Jisonami2Application.class, args);
	}
}
