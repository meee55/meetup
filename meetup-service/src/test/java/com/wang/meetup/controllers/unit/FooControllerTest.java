package com.wang.meetup.controllers.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.ResultActions;

import com.wang.database.mybatis.configuration.MyBatisConfiguration;
import com.wang.database.tx.configuration.TransactionManagerConfigure;
import com.wang.meetup.configuration.ApplicationConfigure;
import com.wang.security.configure.SecurityConfigure;
import com.wang.web.configure.ApplicationCoreConfigure;
import com.wang.web.unit.ControllerTestBase;

@TestPropertySource(locations = "classpath:config/meetup.properties")
@SpringApplicationConfiguration(classes = { MyBatisConfiguration.class, TransactionManagerConfigure.class,
		ApplicationConfigure.class, SecurityConfigure.class, ApplicationCoreConfigure.class, })
public class FooControllerTest extends ControllerTestBase {

	@Test
	public void TestFooRest() throws Exception {
		ResultActions resultActions;

		resultActions = mockMvc.perform(get("/foo").session(session).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON));
		resultActions.andDo(print()).andExpect(status().isOk());
	}
}
