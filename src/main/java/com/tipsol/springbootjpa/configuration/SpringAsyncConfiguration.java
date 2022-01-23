package com.tipsol.springbootjpa.configuration;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SpringAsyncConfiguration implements AsyncConfigurer {

	public static final Logger LOGGER = LoggerFactory.getLogger(SpringAsyncConfiguration.class);
	
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(2);
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (Throwable ex, Method method, Object... params)-> {
			LOGGER.info("Exception message: {} " , ex.getMessage());
			LOGGER.info("Exception occured in method:{} " , method.getName());
				for (Object o : params) {
					LOGGER.info("Parameters of the Method are: {} " , o);
				}
			};
	}
}
