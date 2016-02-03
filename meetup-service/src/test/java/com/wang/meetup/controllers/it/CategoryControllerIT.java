package com.wang.meetup.controllers.it;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.wang.meetup.MainClass;
import com.wang.security.authentication.filter.EnhancedBasicAuthenticationFilter.IEncryptionManager;
import com.wang.web.it.IntegrationTestBase;

@SpringApplicationConfiguration(classes = MainClass.class)
public class CategoryControllerIT extends IntegrationTestBase {

	@Override
	public String getUsername() {
		return "user";
	}

	@Override
	public String getPassword() {
		return "user";
	}

	@Override
	public String encrypt(String str) throws Exception {
		return encryptionManager.encrypt(str);
	}

	@Value("${local.server.port}") private int port;
	@Autowired(required = false) IEncryptionManager encryptionManager;
	@Value("${authentication.filter.enhanced_basic:true}") boolean enhancedBasic;

	@Test
	public void testCategoryRest() throws Exception {
		@SuppressWarnings("rawtypes") ResponseEntity<List> entity = get(
				"http://localhost:" + this.port + "/rest/v1/category", MediaType.ALL,
				Arrays.asList(MediaType.APPLICATION_XML), List.class);

		Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
		Assert.assertTrue(entity.getBody().contains("catetory"));
	}
}
