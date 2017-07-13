package com.sanvito.pokereval.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanvito.Calculator;
import com.sanvito.Results;

@RestController
@EnableAutoConfiguration
public class DecisionController {

	@RequestMapping("/equity")
	public @ResponseBody Map<String, Double> action(@RequestParam String h1, @RequestParam String h2) {
		System.setProperty("java.library.path", "D:/git/StatisticService/src/main/resources/dll");
		String board = "";
		String dead = "";
		Results r = Calculator.calc(h1 + ":" + h2, board, dead, 1000000);
		Map<String, Double> result = new HashMap<String, Double>();
		for (int i = 0; i < r.getSize(); i++) {
			result.put("hand_" + i, r.getEv().get(i));
		}
		return result;
	}
}