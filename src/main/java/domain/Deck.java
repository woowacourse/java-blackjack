package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Deck {
	private final List<Card> cards;

	public Card distributeCard() {
		final int cardSize = cards.size();
		if (cardSize <= 0) {
			throw new IllegalStateException("덱의 카드가 다 소진되었습니다.");
		}
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

	public List<Card> getCards() {
		return this.cards;
	}
}
