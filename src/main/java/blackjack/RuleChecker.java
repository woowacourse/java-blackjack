package blackjack;

import domain.user.User;

public class RuleChecker {
	private static final int BLACKJACK_SCORE = 21;
	private static final int BLACKJACK_CARD_SIZE = 2;
	private static final int BUST_MIN_SCORE = 22;

	public boolean isBlackjack(User user) {
		return user.getCardsSize() == BLACKJACK_CARD_SIZE
			&& user.calculateScore() == BLACKJACK_SCORE;
	}

	public boolean isBust(User user) {
		return user.calculateScore() >= BUST_MIN_SCORE;
	}
}
