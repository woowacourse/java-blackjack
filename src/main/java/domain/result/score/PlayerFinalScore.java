package domain.result.score;

import domain.user.User;

public class PlayerFinalScore extends FinalScore {
	public PlayerFinalScore(User user) {
		super(user);
	}

	@Override
	public boolean isBigger(FinalScore finalScore) {
		return finalScore.isSmaller(score);
	}

	@Override
	public boolean isSmaller(int score) {
		return this.score <= score;
	}

}
