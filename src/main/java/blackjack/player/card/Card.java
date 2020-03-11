package blackjack.player.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.player.card.component.CardNumber;
import blackjack.player.card.component.Symbol;

public class Card {

	private final Symbol symbol;
	private final CardNumber cardNumber;

	public Card(Symbol symbol, CardNumber cardNumber) {
		this.symbol = symbol;
		this.cardNumber = cardNumber;
	}

	public static List<Card> getCardCache() {
		return CardCache.getCardCache();
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

	private static class CardCache {
		private static final List<Card> cards;

		static {
			cards = Arrays.stream(CardNumber.values())
				.map(CardCache::makeCards)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		}

		private CardCache() {
		}

		private static List<Card> makeCards(CardNumber cardNumber) {
			return Arrays.stream(Symbol.values())
				.map(symbol -> new Card(symbol, cardNumber))
				.collect(Collectors.toList());
		}

		public static List<Card> getCardCache() {
			return new ArrayList<>(cards);
		}

		public static Card getCard(Symbol symbol, CardNumber cardNumber) {
			return cards.stream()
				.filter(card -> card.symbol == symbol)
				.filter(card -> card.cardNumber == cardNumber)
				.findFirst()
				.orElseThrow(AssertionError::new);
		}
	}

}
