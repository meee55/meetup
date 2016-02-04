package com.wang.meetup.controllers.it;

import org.springframework.beans.factory.annotation.Autowired;

import com.wang.security.authentication.filter.EnhancedBasicAuthenticationFilter.IEncryptionManager;
import com.wang.web.it.IntegrationTestBase;

public class ITestBase extends IntegrationTestBase {

	@Override
	public String getUsername() {
		return "user";
	}

	@Override
	public String getPassword() {
		return "user";
	}

	@Autowired(required = false) IEncryptionManager encryptionManager;

	@Override
	public String encrypt(String str) throws Exception {
		if (this.getEnhancedBasic()) {
			return encryptionManager.encrypt(str);
		}
		return super.encrypt(str);
	}
}
