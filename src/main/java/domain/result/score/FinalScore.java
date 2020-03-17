package domain.result.score;

import java.util.Objects;

import domain.user.User;

public abstract class FinalScore {
	protected final int score;
	private final User user;

	public FinalScore(User user) {
		this.score = user.calculateScore();
		this.user = Objects.requireNonNull(user);
	}

	public boolean isBust() {
		return user.isBust();
	}

	public boolean isBlackjack() {
		return user.isBlackjack();
	}

	public abstract boolean isBigger(FinalScore score);

	public abstract boolean isSmaller(int score);
}
