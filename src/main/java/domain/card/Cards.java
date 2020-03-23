package domain.card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import domain.score.Score;

public class Cards {
	private static final int BLACKJACK_CARD_SIZE = 2;
	private static final String OUT_OF_CARD_SIZE_EXCEPTION_MESSAGE = "인자의 값이 카드 갯수보다 큽니다.";

	private final List<Card> cards;

	public Cards(List<Card> cards) {
		this.cards = new ArrayList<>(Objects.requireNonNull(cards));
	}

	public void add(Card card) {
		cards.add(Objects.requireNonNull(card));
	}

	public boolean isBlackjack() {
		return cards.size() == BLACKJACK_CARD_SIZE && calculateScore().isMaximum();
	}

	public boolean isBust() {
		return calculateScore().isBust();
	}

	public Score calculateScore() {
		return Score.calculateTotal(cards);
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(cards);
	}

	public List<Card> getSubList(int length) {
		validateArgument(length);
		return Collections.unmodifiableList(cards.subList(0, length));
	}

	private void validateArgument(int length) {
		if (cards.size() < length) {
			throw new IllegalArgumentException(OUT_OF_CARD_SIZE_EXCEPTION_MESSAGE);
		}
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null || getClass() != object.getClass()) {
			return false;
		}
		Cards that = (Cards)object;
		return Objects.equals(this.cards, that.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}

	@Override
	public String toString() {
		return "Cards{" +
			"cards=" + cards +
			'}';
	}
}
