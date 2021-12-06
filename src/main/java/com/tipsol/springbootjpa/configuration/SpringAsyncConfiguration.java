package com.tipsol.springbootjpa.configuration;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SpringAsyncConfiguration implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(1);
		executor.setMaxPoolSize(1);
		executor.setQueueCapacity(2);
		executor.initialize();
		return executor;
	}

	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {

			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				System.out.println("Exception message: " + ex.getMessage());
				System.out.println("Exception occured in method: " + method.getName());
				for (Object o : params) {
					System.out.println("Parameters of the Method are: " + o);
				}
			}

		};
	}
}
