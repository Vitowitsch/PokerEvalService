package com.sanvito.pokereval.rest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sanvito.pokereval.config.SpringConfig;
import com.sanvito.pokereval.config.WebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = { WebConfig.class, SpringConfig.class })
public class RestTest {

	private static final Logger logger = LogManager.getLogger(RestTest.class);

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	// private void endHand(String handId) throws Exception {
	// mvc.perform(post("/endhand").param("handId",
	// handId)).andExpect(jsonPath("$.success", is(true)));
	// }

	private JSONObject getEquity(String h1, String h2) throws Exception {
		ResultActions response = mvc.perform(post("/equity").param("h1", h1).param("h2", h2));
		return new JSONObject(response.andReturn().getResponse().getContentAsString());
	}

	@Test
	public void equity() throws Exception {
		JSONObject result = getEquity("4hqd5h6h", "js9h8h2h");
		logger.info(result.toString());
		// logger.info("result: " + "P1 " + result.getString("status") + ", " +
		// p1State.getString("card1") + " "
		// + p1State.getString("card2"));
	}
}
