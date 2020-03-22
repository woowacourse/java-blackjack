package com.blackjack.domain;

import java.util.stream.Stream;

public enum HitDecideType {
	HIT("y"),
	STAND("n");

	private String symbol;

	HitDecideType(String symbol) {
		this.symbol = symbol;
	}

	public static HitDecideType of(String symbol) {
		return Stream.of(values())
				.filter(hitDecideType -> hitDecideType.isSameSymbol(symbol))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("y 또는 n을 입력해주세요."));
	}

	private boolean isSameSymbol(String symbol) {
		return this.symbol.equals(symbol);
	}

	public boolean isHit() {
		return HIT.equals(this);
	}
}
