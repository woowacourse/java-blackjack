package com.blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
	private static final String SYMBOL_OR_TYPE_IS_NULL_MESSAGE = "symbol 또는 type은 null값을 가질 수 없습니다.";

	private final Symbol symbol;
	private final Type type;

	private Card(Symbol symbol, Type type) {
		validateNull(symbol, type);
		this.symbol = symbol;
		this.type = type;
	}

	public static Card valueOf(Symbol symbol, Type type) {
		return CardCache.CARD_CACHE
				.stream()
				.filter(card -> card.isSameCard(symbol, type))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException(SYMBOL_OR_TYPE_IS_NULL_MESSAGE));
	}

	public static List<Card> values() {
		return Collections.unmodifiableList(CardCache.CARD_CACHE);
	}

	private void validateNull(Symbol symbol, Type type) {
		if (Objects.isNull(symbol) || Objects.isNull(type)) {
			throw new IllegalArgumentException(SYMBOL_OR_TYPE_IS_NULL_MESSAGE);
		}
	}

	public boolean isAce() {
		return symbol.isAce();
	}

	private boolean isSameCard(Symbol symbol, Type type) {
		return isSameSymbol(symbol) && isSameType(type);
	}

	private boolean isSameSymbol(Symbol symbol) {
		return this.symbol.equals(symbol);
	}

	private boolean isSameType(Type type) {
		return this.type.equals(type);
	}

	public int getScore() {
		return symbol.getScore();
	}

	@Override
	public String toString() {
		return symbol.toString() + type.toString();
	}

	private static class CardCache {
		private static final List<Card> CARD_CACHE = new ArrayList<>();

		static {
			CARD_CACHE.addAll(generateCards());
		}

		private static List<Card> generateCards() {
			return Stream.of(Symbol.values())
					.flatMap(CardCache::generateCardsBySymbol)
					.collect(Collectors.toList());
		}

		private static Stream<Card> generateCardsBySymbol(Symbol symbol) {
			return Stream.of(Type.values())
					.map(type -> new Card(symbol, type));
		}
	}
}
