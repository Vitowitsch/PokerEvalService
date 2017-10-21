package com.sanvito.pokereval.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sanvito.Calculator;
import com.sanvito.Results;

@Service
public class DecisionLogicImpl implements DecisionLogic{

	public Map<String, Double> getEq(String board, String dead, String hands) {
		Results r = Calculator.calc(hands, board, dead, 500);
		Map<String, Double> result = new HashMap<String, Double>();
		hands = hands.replaceAll(";", ", ");
		String[] hands_ = hands.split(":");
		for (int i = 0; i < r.getSize(); i++) {
			result.put(hands_[i], (Double) r.getEv().get(i));
		}
		return result;
	}
}
