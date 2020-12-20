package com.bluering.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties")
public class DBConfiguration {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfigs() {  //히카리 cp 객체를 생성한다. 히카리cp는 커넥셕푼 라이브러리 중 하나임
		return new HikariConfig();
	}
	
	@Bean
	public DataSource dataSource() {  //데이터 소스 객체를 생성한다 .커넥션 풀을 지원하기위한 인터페이스
		// 커넥션풀은 커넥션 객체를 생성해두고 데이터베이스에 접근하는 사용자들에게 미리 생성해둔 커넥션을 제공했다가 다시 돌려 받는 방법
		return new HikariDataSource(hikariConfigs());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean(); // 마이바티스와 스프링의 연동모듈임(SqlSessionFactoryBean
		factoryBean.setDataSource(dataSource());
		factoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*Mapper.xml")); //패턴에 포함된 XML매버를 인식하도록
		factoryBean.setConfiguration(mybatisConfig());
		return factoryBean.getObject();
	}
	
	@Bean
	@ConfigurationProperties(prefix="mybatis.configutation")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		return new org.apache.ibatis.session.Configuration();
	}

	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {  //SqlSessionTempate은 SqlSessionFactory를 통해 생성되고 커밋,롤백 등 SQL의 실행에 필요한 모든 메서드를 갖는 객체
		return new SqlSessionTemplate(sqlSessionFactory());
	}
}
