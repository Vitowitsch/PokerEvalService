package com.sanvito.pokereval.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanvito.Calculator;
import com.sanvito.Results;

@RestController
@EnableAutoConfiguration
public class DecisionController {

	@RequestMapping("/equity")
	public @ResponseBody Map<String, Double> equity(String board, String dead, String hands) {
		Results r = Calculator.calc(hands, board, dead, 1000000);
		Map<String, Double> result = new HashMap<String, Double>();
		hands = hands.replaceAll(";", ", ");
		String[] hands_ = hands.split(":");
		for (int i = 0; i < r.getSize(); i++) {
			result.put(hands_[i], (Double) r.getEv().get(i));
		}
		return result;
	}
}