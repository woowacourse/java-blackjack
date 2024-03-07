package domain.card;

import java.util.ArrayList;
import java.util.List;

public class UnShuffledDeckGenerator {
	private static final UnShuffledDeckGenerator GENERATOR = new UnShuffledDeckGenerator();

	private UnShuffledDeckGenerator() {
	}

	public static UnShuffledDeckGenerator getInstance() {
		return GENERATOR;
	}

	public Deck generate() {
		return new Deck(createCards());
	}

	private List<Card> createCards() {
		List<Card> cards = new ArrayList<>();

		for (Suit suit : Suit.values()) {
			cards.addAll(createSuitCards(suit));
		}

		return cards;
	}

	private List<Card> createSuitCards(Suit suit) {
		List<Card> cards = new ArrayList<>();

		for (Rank rank : Rank.values()) {
			cards.add(new Card(suit, rank));
		}

		return cards;
	}
}
