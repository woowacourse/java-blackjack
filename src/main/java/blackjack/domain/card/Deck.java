package blackjack.domain.card;

import java.util.Collections;
import java.util.Stack;

import blackjack.domain.card.pattern.Denomination;
import blackjack.domain.card.pattern.Suit;

public class Deck {

	private static final int EMPTY_DECK_SIZE = 0;

	private final Stack<Card> cards;

	private Deck(final Stack<Card> cards) {
		this.cards = cards;
	}

	public static Deck initializeDeck() {
		Stack<Card> cards = new Stack<>();
		for (Suit suit : Suit.values()) {
			pushCard(cards, suit);
		}
		Collections.shuffle(cards);
		return new Deck(cards);
	}

	private static void pushCard(final Stack<Card> cards, final Suit suit) {
		for (Denomination denomination : Denomination.values()) {
			cards.push(new Card(suit, denomination));
		}
	}

	public Card draw() {
		checkCardSize();
		return cards.pop();
	}

	private void checkCardSize() {
		if (size() == EMPTY_DECK_SIZE) {
			throw new IllegalStateException("[ERROR] 더이상 카드를 뽑을 수 없습니다.");
		}
	}

	private int size() {
		return cards.size();
	}
}
