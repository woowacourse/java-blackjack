package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class ShuffledDeckGenerator {
	private static final ShuffledDeckGenerator INSTANCE = new ShuffledDeckGenerator();

	private ShuffledDeckGenerator() {
	}

	public static ShuffledDeckGenerator getInstance() {
		return INSTANCE;
	}

	public Deck generate() {
		Deck deck = new Deck(createCards());
		deck.shuffle();
		return deck;
	}

	private List<Card> createCards() {
		List<Card> cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			cards.addAll(createCardsOfSuit(suit));
		}

		return cards;
	}

	private List<Card> createCardsOfSuit(Suit suit) {
		List<Card> cards = new ArrayList<>();
		for (Rank rank : Rank.values()) {
			cards.add(new Card(suit, rank));
		}

		return cards;
	}
}
