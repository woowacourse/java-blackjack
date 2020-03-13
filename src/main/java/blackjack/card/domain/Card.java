package blackjack.card.domain;

import blackjack.card.domain.component.CardNumber;
import blackjack.card.domain.component.Symbol;

import java.util.Arrays;
import java.util.stream.Stream;

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

	public static Card[] values() {
		return CardCache.cache;
	}

	public boolean isAce() {
		return this.cardNumber == CardNumber.ACE;
	}

	public int getScore() {
		return this.cardNumber.getNumber();
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
		private static final Card[] cache;

		static {
			cache = Arrays.stream(CardNumber.values())
					.flatMap(CardCache::makeCards)
					.toArray(Card[]::new);
		}

		private CardCache() {
		}

		private static Stream<Card> makeCards(CardNumber cardNumber) {
			return Arrays.stream(Symbol.values())
				.map(symbol -> new Card(symbol, cardNumber));
		}

		public static Card of(Symbol symbol, CardNumber cardNumber) {
			validate(symbol, cardNumber);
			return Arrays.stream(cache)
					.filter(card -> card.symbol == symbol)
					.filter(card -> card.cardNumber == cardNumber)
					.findFirst()
					.orElseThrow(AssertionError::new);
		}

		private static void validate(Symbol symbol, CardNumber cardNumber) {
			if (symbol == null) {
				throw new IllegalArgumentException("Symbol이 비어있습니다.");
			}
			if (cardNumber == null) {
				throw new IllegalArgumentException("CardNumber가 비어있습니다.");
			}
		}
	}

}
