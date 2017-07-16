package com.sanvito.pokereval.rest;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.lessThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.sanvito.pokereval.config.SpringConfig;
import com.sanvito.pokereval.config.WebConfig;

//examples at https://github.com/mitpokerbots/pbots_calc

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = { WebConfig.class, SpringConfig.class })
public class RestTest {

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	private String formatHands(String[] hands) {
		String handsStr = "";
		for (String hand : hands) {
			if (!handsStr.isEmpty()) {
				handsStr += ":";
			}
			handsStr += hand;
		}
		return handsStr;
	}

	private ResultActions getEquity(String board, String dead, String... hands) throws Exception {
		MockHttpServletRequestBuilder builder = post("/equity").param("board", board).param("dead", dead);
		builder.param("hands", formatHands(hands));
		return mvc.perform(builder);
	}

	@Test
	public void preflopEquity() throws Exception {
		ResultActions result = getEquity("", "", "AhAdKhKd", "QhQdJhJd");
		result.andExpect(jsonPath("$.AhAdKhKd", notNullValue()));
		result.andExpect(jsonPath("$.AhAdKhKd").value(Matchers.greaterThan(0.5)));
		result.andExpect(jsonPath("$.QhQdJhJd").value(lessThan(0.5)));
	}

	@Test
	public void handAgainstSuitedHandRange() throws Exception {
		ResultActions result = getEquity("", "", "8Ts", "8sTd");
		result.andExpect(jsonPath("$.8Ts").value(Matchers.greaterThan(0.51)));
		result.andExpect(jsonPath("$.8sTd").value(lessThan(0.49)));
	}

	@Test
	public void handAgainstOffSuitHandRange() throws Exception {
		ResultActions result = getEquity("", "", "8Ts", "8To");
		result.andExpect(jsonPath("$.8Ts").value(Matchers.greaterThan(0.5)));
		result.andExpect(jsonPath("$.8To").value(lessThan(0.5)));
	}

	@Test
	public void queensAgainstJJUp() throws Exception {
		ResultActions result = getEquity("", "", "QQ", "JJ+");
		result.andExpect(jsonPath("$.JJ+").value(Matchers.greaterThan(0.5)));
		result.andExpect(jsonPath("$.QQ").value(lessThan(0.5)));
	}

	@Test
	public void twoRanged() throws Exception {
		ResultActions result = getEquity("", "", "88-TT", "55-66");
		result.andExpect(jsonPath("$.88-TT").value(Matchers.greaterThan(0.5)));
		result.andExpect(jsonPath("$.55-66").value(lessThan(0.5)));
	}

	@Test
	public void randomTwoCardHand() throws Exception {
		ResultActions result = getEquity("", "", "xx", "72o");
		result.andExpect(jsonPath("$.xx").value(Matchers.greaterThan(0.5)));
		result.andExpect(jsonPath("$.72o").value(lessThan(0.5)));
	}

	@Test
	public void pairAgainstPairRange() throws Exception {
		ResultActions result = getEquity("", "", "88", "8s8d");
		result.andExpect(jsonPath("$.88").value(Matchers.greaterThan(0.50)));
		result.andExpect(jsonPath("$.8s8d").value(lessThan(0.50)));
	}

	@Test
	public void handAgainstTwoHandRange() throws Exception {
		ResultActions result = getEquity("", "", "8sTd;8sTc", "9c9d");
		result.andExpect(jsonPath("$.8sTd;8sTc").value(Matchers.greaterThan(0.51)));
		result.andExpect(jsonPath("$.9c9d").value(lessThan(0.49)));
	}

	@Test
	public void preflopEquityHoldem() throws Exception {
		ResultActions result = getEquity("", "", "AhAd", "QhQd");
		result.andExpect(jsonPath("$.AhAd").value(Matchers.greaterThan(0.7)));
		result.andExpect(jsonPath("$.QhQd").value(lessThan(0.3)));
	}

	@Test
	public void preflopEquityHoldemRandomHand() throws Exception {
		ResultActions result = getEquity("", "", "AhAd", "x");
		result.andExpect(jsonPath("$.AhAd").value(Matchers.greaterThan(0.7)));
		result.andExpect(jsonPath("$.x").value(lessThan(0.3)));
	}

	@Test
	public void postFlopEquity() throws Exception {
		ResultActions result = getEquity("Qc3d3h", "", "AhAdKhKd", "QhQdJhJd");
		result.andExpect(jsonPath("$.AhAdKhKd", notNullValue()));
		result.andExpect(jsonPath("$.AhAdKhKd").value(Matchers.lessThan(0.5)));
		result.andExpect(jsonPath("$.QhQdJhJd").value(Matchers.greaterThan(0.5)));
	}

	@Test
	public void deadCards() throws Exception {
		ResultActions result = getEquity("QcQs3c", "AsAhKcKdTsTdThTc", "AcAdKhKs", "QhQdJhJd");
		result.andExpect(jsonPath("$.AcAdKhKs", notNullValue()));
		result.andExpect(jsonPath("$.QhQdJhJd", notNullValue()));
		result.andExpect(jsonPath("$.QhQdJhJd").value(Matchers.greaterThan(0.97)));
		result.andExpect(jsonPath("$.AcAdKhKs").value(lessThan(new BigDecimal(0.02))));
	}

}
