package com.tipsol.springbootjpa;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * {@summary This is }
 * 
 * @author 1000475
 */
@SpringBootApplication
//@EnableJpaAuditing
@EnableAsync
public class SpringbootjpaApplication extends SpringBootServletInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger(SpringbootjpaApplication.class);

	/**
	 * @author 1000475 Main Method
	 * @param args
	 */
	public static void main(final String[] args) {
		SpringApplication.run(SpringbootjpaApplication.class, args);
		LOGGER.info("----Started------");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SpringbootjpaApplication.class);
	}

	/**
	 * @author 1000475
	 * @return ModelMapper
	 */
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
