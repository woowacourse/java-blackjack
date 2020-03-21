package domain.card;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static domain.user.User.NULL_CAN_NOT_BE_A_PARAMETER_EXCEPTION_MESSAGE;

public class Cards {
	private static final int BLACKJACK_SCORE = 21;
	private static final int INITIAL_CARDS_SIZE = 2;
	private static final int BURST_SCORE = 0;

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

	public int calculateScore() {
		int rawScore = cards.stream()
				.mapToInt(Card::getScore)
				.sum();

		int specialScore = cards.stream()
				.filter(card -> card.getSymbol().getPromotableJudge(rawScore))
				.mapToInt(card -> card.getSymbol().getPromotionScore())
				.findFirst()
				.orElse(BURST_SCORE);

		if (rawScore + specialScore > BLACKJACK_SCORE) {
			return BURST_SCORE;
		}
		return rawScore + specialScore;
	}

	public boolean isNotBurst() {
		return !cards.isEmpty() && calculateScore() != BURST_SCORE;
	}

	public boolean isNotBlackjack() {
		return !(hasInitialSize() && calculateScore() == BLACKJACK_SCORE);
	}

	public boolean isBlackJack() {
		return hasInitialSize() && calculateScore() == BLACKJACK_SCORE;
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
