package com.cafe24.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cafe24.security.AuthAccessInterceptor;
import com.cafe24.security.AuthLoginInterceptor;
import com.cafe24.security.AuthLogoutInterceptor;


@Configuration
@EnableWebMvc
public class SecurityConfig extends WebMvcConfigurerAdapter{

	//
	// Interceptor
	//
	
	@Bean
	public AuthLoginInterceptor authLoginInterceptor() {
		return new AuthLoginInterceptor();
	}
	
	@Bean
	public AuthLogoutInterceptor authLogoutInterceptor() {
		return new AuthLogoutInterceptor();
	}
	
	@Bean
	public AuthAccessInterceptor authAccessInterceptor() {
		return new AuthAccessInterceptor();
	}
		
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry
		.addInterceptor(authLoginInterceptor())
		.addPathPatterns("/user/auth");
		
		registry
		.addInterceptor(authLogoutInterceptor())
		.addPathPatterns("/user/logout");
		
		registry
		.addInterceptor(authAccessInterceptor())
		.addPathPatterns("/**")
		.excludePathPatterns("/user/login")
		.excludePathPatterns("/user/logout")
		.excludePathPatterns("/user/join")
		.excludePathPatterns("/sh")
		.excludePathPatterns("/user/api/**");
		
	}
	
	
}
