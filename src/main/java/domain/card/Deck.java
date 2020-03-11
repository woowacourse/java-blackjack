package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class Deck {
	private final Stack<Card> deck;

	public Deck(List<Card> cards) {
		deck = new Stack<>();
		cards.forEach(deck::push);
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}

	public Card pop() {
		return deck.pop();
	}
}
