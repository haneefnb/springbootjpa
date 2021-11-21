package com.tipsol.springbootjpa.interceptors;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class AcceptHeaderResolver extends AcceptHeaderLocaleResolver{
	
	List<Locale> LOCALES = Arrays.asList(new Locale("en"), 
			new Locale("ar"));
	
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		String header = request.getHeader("Accept-Language");
		return header == null || header.isEmpty() ? Locale.getDefault():
			Locale.lookup(Locale.LanguageRange.parse(header), LOCALES);
	}
	
	@Bean
	public ResourceBundleMessageSource messageResource() {
		ResourceBundleMessageSource bundle = new ResourceBundleMessageSource();
		bundle.setBasename("messages");
		bundle.setDefaultEncoding("UTF-8");
		bundle.setUseCodeAsDefaultMessage(true);
		return bundle;
	}
	
}
