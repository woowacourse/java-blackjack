package blackjack.player.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
		return this.cardNumber.number;
	}

	enum CardNumber {
		ACE(1),
		TWO(2),
		THREE(3),
		FOUR(4),
		FIVE(5),
		SIX(6),
		SEVEN(7),
		EIGHT(8),
		NINE(9),
		TEN(10),
		JACK(10),
		QUEEN(10),
		KING(10);

		private final int number;

		CardNumber(int number) {
			this.number = number;
		}
	}

	enum Symbol {
		HEART("하트"),
		SPADE("스페이드"),
		DIAMOND("다이아몬드"),
		CLUB("클럽");

		private final String name;

		Symbol(String name) {
			this.name = name;
		}
	}

	private static class CardCache {
		private static final List<Card> cards;

		static {
			cards = Arrays.stream(Card.CardNumber.values())
				.map(CardCache::makeCards)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		}

		private CardCache() {
		}

		private static List<Card> makeCards(Card.CardNumber cardNumber) {
			return Arrays.stream(Card.Symbol.values())
				.map(symbol -> new Card(symbol, cardNumber))
				.collect(Collectors.toList());
		}

		public static List<Card> getCardCache() {
			return new ArrayList<>(cards);
		}
	}

}
