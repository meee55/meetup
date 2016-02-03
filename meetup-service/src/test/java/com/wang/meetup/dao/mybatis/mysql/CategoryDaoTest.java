package com.wang.meetup.dao.mybatis.mysql;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.wang.database.mybatis.configuration.MyBatisConfiguration;
import com.wang.database.tx.configuration.TransactionManagerConfigure;
import com.wang.meetup.configuration.ApplicationConfigure;
import com.wang.meetup.dao.models.Category;
import com.wang.web.unit.DbUnitTestBase;

@TestPropertySource(value = "classpath:config/mybatis.properties")
@SpringApplicationConfiguration(classes = { MyBatisConfiguration.class, TransactionManagerConfigure.class,
		ApplicationConfigure.class })
@DatabaseSetup(connection = "dataSource", value = { "classpath:dbunit/Categories.xml" })
@Transactional
public class CategoryDaoTest extends DbUnitTestBase {
	Logger logger = Logger.getLogger(this.getClass());

	@Autowired CategoryDao categoryDao;

	@Test
	public void TestCategorySelectByExample() throws Exception {
		logger.debug("TestCategorySelectByExample() ");

		Category category = new Category();
		category.setId(101L);
		List<Category> catetories = categoryDao.queryByExample(category);
		Assert.assertEquals(1, catetories.size());
	}
}
