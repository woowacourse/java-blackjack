package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import static domain.user.User.NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE;

public class Deck {
	private final Stack<Card> deck;

	public Deck(List<Card> cards) {
		Objects.requireNonNull(cards, NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE);
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
