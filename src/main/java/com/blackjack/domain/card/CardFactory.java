package com.blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CardFactory {
	private static final Set<Card> cards;

	static {
		cards = Arrays.stream(CardNumber.values())
				.flatMap(CardFactory::createCards)
				.collect(Collectors.toSet());
	}

	private CardFactory() {
	}

	private static Stream<Card> createCards(CardNumber cardNumber) {
		return Arrays.stream(CardSymbol.values())
				.map(cardSymbol -> new Card(cardNumber, cardSymbol));
	}

	static Set<Card> create() {
		return Collections.unmodifiableSet(cards);
	}
}
