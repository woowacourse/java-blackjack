package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ShuffledDeckGenerator {
	private static final ShuffledDeckGenerator INSTANCE = new ShuffledDeckGenerator();

	private ShuffledDeckGenerator() {
	}

	public static ShuffledDeckGenerator getInstance() {
		return INSTANCE;
	}

	public Deck generate() {
		return new Deck(createShuffledCards());
	}

	private List<Card> createShuffledCards() {
		List<Card> cards = new ArrayList<>();
		for (Suit suit : Suit.values()) {
			cards.addAll(createCardsOfSuit(suit));
		}

		Collections.shuffle(cards);
		return cards;
	}

	private List<Card> createCardsOfSuit(final Suit suit) {
		List<Card> cards = new ArrayList<>();
		for (Rank rank : Rank.values()) {
			cards.add(Card.of(suit, rank));
		}

		return cards;
	}
}
