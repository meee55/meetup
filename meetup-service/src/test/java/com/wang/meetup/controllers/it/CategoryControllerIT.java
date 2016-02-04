package com.wang.meetup.controllers.it;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.wang.meetup.MainClass;

@SpringApplicationConfiguration(classes = MainClass.class)
public class CategoryControllerIT extends ITestBase {
	@Test
	public void testCategoryRest() throws Exception {
		@SuppressWarnings("rawtypes") ResponseEntity<List> entity = get(
				"http://localhost" + (0 == port ? "" : (":" + port)) + "/rest/v1/category",
				Arrays.asList(MediaType.APPLICATION_XML), List.class);

		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
		Assert.assertTrue(entity.getBody().contains("catetory"));
	}
}
