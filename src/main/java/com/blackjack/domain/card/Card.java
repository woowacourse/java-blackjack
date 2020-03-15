package com.blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
	private static final String SYMBOL_OR_TYPE_IS_NULL_MESSAGE = "denomination 또는 suit는 null을 가질 수 없습니다.";

	private final Denomination denomination;
	private final Suit suit;

	private Card(Denomination denomination, Suit suit) {
		validateNull(denomination, suit);
		this.denomination = denomination;
		this.suit = suit;
	}

	public static Card valueOf(Denomination denomination, Suit suit) {
		return CardCache.CARD_CACHE
				.stream()
				.filter(card -> card.isSameCard(denomination, suit))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException(SYMBOL_OR_TYPE_IS_NULL_MESSAGE));
	}

	public static List<Card> values() {
		return Collections.unmodifiableList(CardCache.CARD_CACHE);
	}

	private void validateNull(Denomination denomination, Suit suit) {
		if (Objects.isNull(denomination) || Objects.isNull(suit)) {
			throw new IllegalArgumentException(SYMBOL_OR_TYPE_IS_NULL_MESSAGE);
		}
	}

	public boolean isAce() {
		return denomination.isAce();
	}

	private boolean isSameCard(Denomination denomination, Suit suit) {
		return isSameSymbol(denomination) && isSameType(suit);
	}

	private boolean isSameSymbol(Denomination denomination) {
		return this.denomination.equals(denomination);
	}

	private boolean isSameType(Suit suit) {
		return this.suit.equals(suit);
	}

	public int getScore() {
		return denomination.getScore();
	}

	@Override
	public String toString() {
		return denomination.toString() + suit.toString();
	}

	private static class CardCache {
		private static final List<Card> CARD_CACHE = new ArrayList<>();

		static {
			CARD_CACHE.addAll(generateCards());
		}

		private static List<Card> generateCards() {
			return Stream.of(Denomination.values())
					.flatMap(CardCache::generateCardsBySymbol)
					.collect(Collectors.toList());
		}

		private static Stream<Card> generateCardsBySymbol(Denomination denomination) {
			return Stream.of(Suit.values())
					.map(suit -> new Card(denomination, suit));
		}
	}
}
