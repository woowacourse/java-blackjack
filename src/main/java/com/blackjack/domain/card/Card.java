package com.blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {
	private final CardNumber cardNumber;
	private final CardSymbol cardSymbol;

	private Card(CardNumber cardNumber, CardSymbol cardSymbol) {
		validateNull(cardNumber, cardSymbol);
		this.cardNumber = cardNumber;
		this.cardSymbol = cardSymbol;
	}

	public static Card valueOf(CardNumber cardNumber, CardSymbol cardSymbol) {
		return CardCache.CARD_CACHE
				.stream()
				.filter(card -> card.isSameCard(cardNumber, cardSymbol))
				.findAny()
				.orElse(new Card(cardNumber, cardSymbol));
	}

	public static List<Card> values() {
		return Collections.unmodifiableList(CardCache.CARD_CACHE);
	}

	private void validateNull(CardNumber cardNumber, CardSymbol cardSymbol) {
		if (Objects.isNull(cardNumber) || Objects.isNull(cardSymbol)) {
			throw new IllegalArgumentException("denomination 또는 suit는 null을 가질 수 없습니다.");
		}
	}

	public boolean isAce() {
		return cardNumber.isAce();
	}

	private boolean isSameCard(CardNumber cardNumber, CardSymbol cardSymbol) {
		return isSameSymbol(cardNumber) && isSameType(cardSymbol);
	}

	private boolean isSameSymbol(CardNumber cardNumber) {
		return this.cardNumber.equals(cardNumber);
	}

	private boolean isSameType(CardSymbol cardSymbol) {
		return this.cardSymbol.equals(cardSymbol);
	}

	public int getScore() {
		return cardNumber.getScore();
	}

	@Override
	public String toString() {
		return cardNumber.toString() + cardSymbol.toString();
	}

	private static class CardCache {
		private static final List<Card> CARD_CACHE = new ArrayList<>();

		static {
			CARD_CACHE.addAll(generateCards());
		}

		private static List<Card> generateCards() {
			return Stream.of(CardNumber.values())
					.flatMap(CardCache::generateCardsBySymbol)
					.collect(Collectors.toList());
		}

		private static Stream<Card> generateCardsBySymbol(CardNumber cardNumber) {
			return Stream.of(CardSymbol.values())
					.map(suit -> new Card(cardNumber, suit));
		}
	}
}
