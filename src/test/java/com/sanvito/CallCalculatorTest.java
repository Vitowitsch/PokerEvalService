package com.sanvito;

import org.junit.Test;

public class CallCalculatorTest {

	@Test
	public void test() {
		System.setProperty("java.library.path", "D:/git/StatisticService/src/main/resources/dll");
		String board = "";
		String dead = "";
		String hands = "4hqd5h6h:js9h8h2h";
		Results r = Calculator.calc(hands, board, dead, 1000000);
		for (int i = 0; i < r.getSize(); i++) {
			System.out.println(r.getHands().get(i) + ":" + r.getEv().get(i));
		}
	}

}
