package blackjack.card.domain;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

public class CardDeck {

	private final Stack<Card> blackjackCards;

	private CardDeck(List<Card> cards) {
		this.blackjackCards = new Stack<>();
		this.blackjackCards.addAll(cards);
		Collections.shuffle(blackjackCards);
	}

	public static CardDeck getInstance(List<Card> cards) {
		return new CardDeck(cards);
	}

	public Card draw() {
		checkEmpty();
		return blackjackCards.pop();
	}

	private void checkEmpty() {
		if (blackjackCards.isEmpty()) {
			throw new NoSuchElementException("모든 패를 뽑았습니다.");
		}
	}
}
