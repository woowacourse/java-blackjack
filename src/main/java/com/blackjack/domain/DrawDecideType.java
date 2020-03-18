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
				.filter(drawDecideType -> drawDecideType.isSameSymbol(symbol))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력해주세요."));
	}

	private boolean isSameSymbol(String symbol) {
		return this.symbol.equals(symbol);
	}

	public boolean isDraw() {
		return DRAW.equals(this);
	}
}
