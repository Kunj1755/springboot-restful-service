package com.personal.kunj.springbootrestfulservice;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class SpringbootRestfulServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulServiceApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		// After using AcceptHeaderLocaleResolver, we will not need to configure locale
		// as request parameter/header in every controller method
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	/*
	 * You can replace this code with property "spring.messages.basename=messages"
	 * in application.properties
	 */
	/*
	 * @Bean public ResourceBundleMessageSource messageSource() {
	 * ResourceBundleMessageSource resourceBundleMessageSource = new
	 * ResourceBundleMessageSource();
	 * resourceBundleMessageSource.setBasename("messages"); return
	 * resourceBundleMessageSource; }
	 */
}
