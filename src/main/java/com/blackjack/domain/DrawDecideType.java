package com.blackjack.domain;

import java.util.Arrays;

public enum DrawDecideType {
	DRAW("y"),
	STAND("n");

	private String symbol;

	DrawDecideType(String symbol) {
		this.symbol = symbol;
	}

	public static boolean isDraw(String symbol) {
		Arrays.stream(values())
				.filter(drawDecideType -> drawDecideType.symbol.equals(symbol))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력해주세요."));

		return DRAW.symbol.equals(symbol);
	}
}
