package domain.result.score;

import java.util.Objects;

import blackjack.RuleChecker;
import domain.user.User;

public abstract class FinalScore {
	protected final int score;
	private final boolean isBust;
	private final boolean isBlackjack;

	public FinalScore(User user) {
		Objects.requireNonNull(user);
		RuleChecker ruleChecker = new RuleChecker();
		this.score = user.calculateScore();
		this.isBust = ruleChecker.isBust(user);
		this.isBlackjack = ruleChecker.isBlackjack(user);
	}

	public boolean isBust() {
		return isBust;
	}

	public boolean isBlackjack() {
		return isBlackjack;
	}

	public boolean isBigger(FinalScore finalScore) {
		return Objects.requireNonNull(finalScore).isSmaller(score);
	}

	public abstract boolean isSmaller(int score);
}
