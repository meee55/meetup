package com.wang.meetup.configuration;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.wang.utils.database.DataSourceInitializer;

@EnableAutoConfiguration
@Configuration
@PropertySource(value = { "classpath:config/meetup.properties" })
@MapperScan(basePackages = { "com.wang.meetup.dao.mybatis.mysql" }, sqlSessionFactoryRef = "myBatisSqlSessionFactory")
public class ApplicationConfigure {
	Logger logger = Logger.getLogger(this.getClass());

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@ConfigurationProperties("database.connection")
	@Bean
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@ConditionalOnBean(DataSource.class)
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource,
			@Value("${database.script.schema:classpath:com/wang/meetup/db/scripts/creation.sql}") Resource schemaScript,
			@Value("${database.script.init:classpath:com/wang/meetup/db/scripts/init.sql}") Resource dataScript) {
		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setFlagTableName("Categories");
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator(schemaScript, dataScript));
		return initializer;
	}

	private DatabasePopulator databasePopulator(Resource... scripts) {
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		for (Resource script : scripts)
			populator.addScript(script);
		return populator;
	}

}
