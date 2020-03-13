package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CardDeck {
	private final List<Card> cards;
	private final Stack<Integer> decks;

	public CardDeck() {
		this.cards = CardFactory.create();
		this.decks = new Stack<>();
		for (int index = 0, size = cards.size(); index < size; index++) {
			decks.push(index);
		}
		Collections.shuffle(decks);
	}

	public CardDeck(List<Integer> decks) {
		this.cards = CardFactory.create();
		this.decks = new Stack<>();
		for (Integer order : decks) {
			this.decks.push(order);
		}
	}

	public Card draw() {
		if (decks.isEmpty()) {
			throw new NoSuchElementException("카드 덱이 비어있습니다.");
		}
		return cards.get(decks.pop());
	}
}
