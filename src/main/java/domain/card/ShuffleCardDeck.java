package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class ShuffleCardDeck implements CardDeck {
	private static final String EMPTY_CARD_DECK_EXCEPTION_MESSAGE = "카드 덱이 비어있습니다.";

	private final Stack<Card> cards;

	public ShuffleCardDeck() {
		this.cards = initCards();
	}

	private Stack<Card> initCards() {
		Stack<Card> cards = new Stack<>();
		List<Card> totalCards = CardFactory.getInstance();
		for (Card each : totalCards) {
			cards.push(each);
		}
		Collections.shuffle(cards);
		return cards;
	}

	public Card draw() {
		if (cards.isEmpty()) {
			throw new NoSuchElementException(EMPTY_CARD_DECK_EXCEPTION_MESSAGE);
		}
		return cards.pop();
	}
}
