package com.blackjack.domain.card;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class CardFactory {
	private static List<Card> cards;

	static {
		cards = Arrays.stream(CardNumber.values())
				.flatMap(CardFactory::createCards)
				.collect(Collectors.toList());
	}

	private CardFactory() {
	}

	private static Stream<Card> createCards(CardNumber cardNumber) {
		return Arrays.stream(CardSymbol.values())
				.map(cardSymbol -> new Card(cardNumber, cardSymbol));
	}

	static List<Card> create() {
		return Collections.unmodifiableList(cards);
	}
}
