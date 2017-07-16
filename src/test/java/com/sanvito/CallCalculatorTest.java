package com.sanvito;

import org.junit.Before;
import org.junit.Test;

import com.sanvito.pokereval.config.SpringConfig;

public class CallCalculatorTest {

	@Before
	public void init() {
		SpringConfig c = new SpringConfig();
	}

	@Test
	public void test() {
		String board = "";
		String dead = "";
		String hands = "4hqd5h6h:js9h8h2h";
		Results r = Calculator.calc(hands, board, dead, 1000000);
		for (int i = 0; i < r.getSize(); i++) {
			System.out.println(r.getHands().get(i) + ":" + r.getEv().get(i));
		}
	}

}
