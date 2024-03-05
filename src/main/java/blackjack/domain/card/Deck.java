package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {

	private final LinkedList<Card> cards;

	public Deck() {
		this.cards = Arrays.stream(CardShape.values())
			.flatMap(cardShape -> Arrays.stream(CardNumber.values())
				.map(number -> new Card(cardShape, number)))
			.collect(Collectors.toCollection(LinkedList::new));
	}

	public void shuffle() {
		Collections.shuffle(cards);
	}

	public Card draw() {
		return cards.poll();
	}

	public List<Card> getCards() {
		return new ArrayList<>(cards);
	}
}
