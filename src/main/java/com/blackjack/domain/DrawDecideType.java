package com.blackjack.domain;

import java.util.stream.Stream;

public enum DrawDecideType {
	DRAW("y"),
	STAND("n");

	private String symbol;

	DrawDecideType(String symbol) {
		this.symbol = symbol;
	}

	public static DrawDecideType of(String symbol) {
		return Stream.of(values())
				.filter(drawDecideType -> drawDecideType.isSameOf(symbol))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력해주세요."));
	}

	public static boolean isDraw(String symbol) {
		return DRAW == of(symbol);
	}

	private boolean isSameOf(String symbol) {
		return this.symbol.equals(symbol);
	}
}
