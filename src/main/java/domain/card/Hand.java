package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
	private static final int BLACKJACK_CARD_SIZE = 2;

	private final List<Card> hand;

	private Hand(List<Card> hand) {
		this.hand = hand;
	}

	public static Hand fromEmpty() {
		return new Hand(new ArrayList<>());
	}

	public void add(Card card) {
		hand.add(card);
	}

	public boolean hasTwoCards() {
		return hand.size() == BLACKJACK_CARD_SIZE;
	}

	public int calculate() {
		return hand.stream()
			.mapToInt(Card::getScore)
			.sum();
	}

	public boolean hasAce() {
		return hand.stream()
			.anyMatch(Card::isAce);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(hand);
	}
}
