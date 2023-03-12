package domain;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Denomination;

public class Score {

	private static final int MEANINGFUL_SCORE_MAX = 21;
	private static final int ACE_SUB_NUMBER = 1;

	private final int value;

	private Score(final int value) {
		this.value = value;
	}

	public static Score of(final Cards cards) {
		final int score = getInitialScore(cards);
		final int count = cards.count(Denomination.ACE);
		return new Score(modifyScoreByAce(score, count));
	}

	private static int getInitialScore(final Cards cards) {
		return cards.getCards()
			.stream()
			.mapToInt(Card::getNumber)
			.sum();
	}

	private static int modifyScoreByAce(int score, final int count) {
		for (int i = 0; i < count; i++) {
			score = changeToAceSub(score);
		}
		return score;
	}

	private static int changeToAceSub(int score) {
		if (score > MEANINGFUL_SCORE_MAX) {
			score -= Denomination.ACE.getNumber();
			score += ACE_SUB_NUMBER;
		}
		return score;
	}

	public boolean isBlackJackScore() {
		return value == MEANINGFUL_SCORE_MAX;
	}

	public boolean isBust() {
		return value > MEANINGFUL_SCORE_MAX;
	}

	public int getValue() {
		return value;
	}
}
