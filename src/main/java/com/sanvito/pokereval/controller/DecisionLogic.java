package com.sanvito.pokereval.controller;

import java.util.Map;

public interface DecisionLogic {
	public Map<String, Double> getEq(String board, String dead, String hand);

}
