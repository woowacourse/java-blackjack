package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

	private final List<Card> cards;

	public Deck() {
		List<Card> cards = new ArrayList<>();
		Arrays.stream(Rank.values()).forEach(rank -> {
			Arrays.stream(Suit.values()).map(suit -> new Card(rank, suit)).forEach(cards::add);
		});
		Collections.shuffle(cards);
		this.cards = cards;
	}

	public Card draw() {
		Card card = cards.get(0);
		cards.remove(0);
		return card;
	}
}
