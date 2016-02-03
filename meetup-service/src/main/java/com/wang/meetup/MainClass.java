package com.wang.meetup;

import org.apache.log4j.Logger;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.wang.database.mybatis.configuration.MyBatisConfiguration;
import com.wang.database.tx.configuration.TransactionManagerConfigure;
import com.wang.security.configure.SecurityConfigure;
import com.wang.web.configure.ApplicationCoreConfigure;

@ComponentScan
@Import(value = { SecurityConfigure.class, ApplicationCoreConfigure.class, MyBatisConfiguration.class,
		TransactionManagerConfigure.class })
public class MainClass {
	static final Logger logger = Logger.getLogger(MainClass.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MainClass.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);

		logger.info("Server is running...");
	}

}
