package domain.card;

import domain.result.Score;

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

	public int sumOfCards() {
		return cards.stream()
				.mapToInt(Card::getScore)
				.sum();
	}

	public Score getScore() {
		return Score.from(this);
	}

	public boolean hasAce() {
		return cards.stream()
				.anyMatch(Card::isAce);
	}

	public boolean isBlackJack() {
		return hasTwoCards() && getScore().isEqualTo(Score.BLACKJACK);
	}

	public boolean isNotBlackJack() {
		return !isBlackJack();
	}

	private boolean hasTwoCards() {
		return cards.size() == BLACKJACK_CARD_SIZE;
	}

	public boolean isBust() {
		return getScore().isBiggerThan(Score.BLACKJACK);
	}

	public boolean isBiggerThan(Hand hand) {
		return getScore().isBiggerThan(hand.getScore());
	}

	public boolean isEqualTo(Hand hand) {
		return getScore().isEqualTo(hand.getScore());
	}

	public boolean isLowerThan(Hand hand) {
		return getScore().isLowerThan(hand.getScore());
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
