package blackjack.card.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import blackjack.card.domain.component.CardNumber;
import blackjack.card.domain.component.Symbol;

public class Card {
	private final Symbol symbol;
	private final CardNumber cardNumber;

	private Card(Symbol symbol, CardNumber cardNumber) {
		this.symbol = symbol;
		this.cardNumber = cardNumber;
	}

	public static Card of(Symbol symbol, CardNumber cardNumber) {
		return CardCache.of(symbol, cardNumber);
	}

	public static List<Card> getAllCards() {
		return CardCache.getAllCards();
	}

	public boolean isAce() {
		return cardNumber == CardNumber.ACE;
	}

	public int getScore() {
		return cardNumber.getNumber();
	}

	public int getNumber() {
		return cardNumber.getNumber();
	}

	public String getSymbol() {
		return symbol.getName();
	}

	public String getMessage() {
		return cardNumber.getMessage();
	}

	private static class CardCache {
		private static final List<Card> cache;

		static {
			cache = Arrays.stream(CardNumber.values())
				.flatMap(CardCache::makeCards)
				.collect(Collectors.toList());
		}

		private CardCache() {
		}

		private static Stream<Card> makeCards(CardNumber cardNumber) {
			return Arrays.stream(Symbol.values())
				.map(symbol -> new Card(symbol, cardNumber));
		}

		public static Card of(Symbol symbol, CardNumber cardNumber) {
			return cache.stream()
				.filter(card -> card.symbol == symbol)
				.filter(card -> card.cardNumber == cardNumber)
				.findFirst()
				.orElseThrow(AssertionError::new);
		}

		public static List<Card> getAllCards() {
			return Collections.unmodifiableList(cache);
		}
	}
}
