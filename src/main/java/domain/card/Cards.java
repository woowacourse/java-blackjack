package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static domain.user.User.NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE;

public class Cards {
	private static final int BLACKJACK_SCORE = 21;
	private static final int BURST_START_BOUNDARY = 22;
	private static final int BURST_SCORE = 0;
	private static final int INITIAL_CARDS_SIZE = 2;

	private List<Card> cards;

	public Cards(List<Card> cards) {
		Objects.requireNonNull(cards, NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE);
		this.cards = cards;
	}

	public void add(Card card) {
		Objects.requireNonNull(card, NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE);
		cards.add(card);
	}

	public List<Card> toList() {
		return Collections.unmodifiableList(cards);
	}

	public boolean isNotBlackJack() {
		return !(hasInitialSize() && calculateScore() == BLACKJACK_SCORE);
	}

	public int calculateScore() {
		int score = cards.stream()
				.mapToInt(Card::getScore)
				.sum();

		if (score >= BURST_START_BOUNDARY) {
			return BURST_SCORE;
		}

		int finalScore = score;
		score = cards.stream()
				.mapToInt(card -> card.getScore(finalScore))
				.sum();

		return score;
	}

	public boolean hasInitialSize() {
		return cards.size() == INITIAL_CARDS_SIZE;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cards cards1 = (Cards) o;
		return Objects.equals(cards, cards1.cards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cards);
	}
}
