package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Hand {
	private static final int BLACKJACK_CARD_SIZE = 2;

	private final List<Card> cards;

	private Hand(List<Card> cards) {
		this.cards = cards;
	}

	public static Hand createEmpty() {
		return new Hand(new ArrayList<>());
	}

	public void add(Card card) {
		cards.add(card);
	}

	public boolean hasTwoCards() {
		return cards.size() == BLACKJACK_CARD_SIZE;
	}

	public int sumOfCards() {
		return cards.stream()
				.mapToInt(Card::getScore)
				.sum();
	}

	public boolean hasAce() {
		return cards.stream()
				.anyMatch(Card::isAce);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hand hand = (Hand) o;
		return Objects.equals(cards, hand.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}
}
