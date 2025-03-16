package card;

import game.GameScore;
import value.Count;

public enum Rank {
	ACE(11),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(10),
	QUEEN(10),
	KING(10);

	private static final GameScore ACE_MIN = GameScore.from(1);

	private final GameScore gameScore;

	Rank(final int score) {
		this.gameScore = GameScore.from(score);
	}

	public static GameScore ifOverThanBustScoreAceIsMIN(
		GameScore gameScore, Count aceCount, final GameScore bustGameScore) {
		while (gameScore.isGreaterThan(bustGameScore) && !aceCount.isZero()) {
			gameScore = gameScore
				.minus(ACE.gameScore)
				.plus(ACE_MIN);
			aceCount = aceCount.decrement();
		}
		return gameScore;
	}

	public GameScore sum(final GameScore gameScore) {
		return gameScore.plus(this.gameScore);
	}
}
