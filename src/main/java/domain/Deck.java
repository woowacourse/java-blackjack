package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Deck {
	private final List<Card> cards;

	public Card distributeCard() {
		return cards.remove(cards.size() - 1);
	}

	public Deck() {
		cards = new ArrayList<>();
		Stream.of(Number.values())
			.forEach(number -> Stream.of(Type.values())
				.forEach(type -> cards.add(new Card(number, type)))
			);
		Collections.shuffle(cards);
	}
}
