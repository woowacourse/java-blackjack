package domain.card;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class TestCardDeck implements CardDeck {
	private static final String EMPTY_DECK_EXCEPTION_MESSAGE = "덱이 비었어요";
	private final Deque<Card> cardDeque;

	public TestCardDeck(Card... cards) {
		this.cardDeque = new LinkedList<>(Arrays.asList(cards));
	}

	@Override
	public Card draw() {
		if (cardDeque.isEmpty()) {
			throw new IllegalArgumentException(EMPTY_DECK_EXCEPTION_MESSAGE);
		}
		return cardDeque.pollFirst();
	}
}
