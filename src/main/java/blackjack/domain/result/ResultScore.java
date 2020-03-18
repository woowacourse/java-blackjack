package blackjack.domain.result;

import blackjack.domain.card.Hand;

public class ResultScore {
	private final Score score;
	private final ScoreType scoreType;

	public ResultScore(Score score, ScoreType scoreType) {
		this.score = score;
		this.scoreType = scoreType;
	}

	public static boolean of(Hand hand) {
		Score score = hand.calculateScore();
		ScoreType scoreType = ScoreType.of(score, hand.isInitialDealtSize());

		return new ResultScore(hand.calculateScore(), scoreType);
	}
}
