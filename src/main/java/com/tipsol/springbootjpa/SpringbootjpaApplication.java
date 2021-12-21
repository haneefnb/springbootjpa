package com.tipsol.springbootjpa;

import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class SpringbootjpaApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
		System.out.println("----Started1------");
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("----Application RUnner---------");
		
	}

}
