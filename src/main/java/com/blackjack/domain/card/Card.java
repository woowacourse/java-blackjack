package com.blackjack.domain.card;

import java.util.Objects;

public class Card {
	private final Symbol symbol;
	private final Type type;

	Card(Symbol symbol, Type type) {
		validateNull(symbol, type);
		this.symbol = symbol;
		this.type = type;
	}

	private void validateNull(Symbol symbol, Type type) {
		if (Objects.isNull(symbol) || Objects.isNull(type)) {
			throw new IllegalArgumentException("symbol 또는 type은 null값을 가질 수 없습니다.");
		}
	}
}
