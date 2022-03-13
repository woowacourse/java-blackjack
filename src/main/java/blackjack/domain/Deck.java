package blackjack.domain;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Stream;

public class Deck {
	private static final String EMPTY_MESSAGE = "[ERROR] 덱의 카드가 다 소진되었습니다.";
	private final Stack<Card> cards;

	public Card distributeCard() {
		if (cards.isEmpty()) {
			throw new IllegalStateException(EMPTY_MESSAGE);
		}
		return cards.pop();
	}

	public Deck() {
		cards = new Stack<>();
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
