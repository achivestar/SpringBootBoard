package com.bluering.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bluering.interceptor.LoggerInterceptor;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoggerInterceptor()).excludePathPatterns("/css/**","/fonts/**","/plugin/**","/scripts/**");
		//addInterceptors 메서드를 오버라이딩 하고. 특정 패턴의 주소 URI를 추가 또는 제외한다
		// src/main/resources 디렉토리의 static 폴더에 포함된 정적 리소스 파일을 제외하기 위해
		// excludePathPatterns 메서드를 사용
	}

	
}
