package domain.result;

import domain.user.User;

public class Referee {

	public static boolean isPlayerWin(User player, User dealer) {
		return isPlayerOnlyBlackjack(player, dealer)
			|| isDealerOnlyBust(player, dealer)
			|| isPlayerScoreWin(player, dealer);
	}

	private static boolean isPlayerOnlyBlackjack(User player, User dealer) {
		return player.isBlackjack() && !dealer.isBlackjack();
	}

	private static boolean isDealerOnlyBust(User player, User dealer) {
		return dealer.isBust() && !player.isBust();
	}

	private static boolean isPlayerScoreWin(User player, User dealer) {
		if (player.isBust()) {
			return false;
		}
		return player.calculateScore() > dealer.calculateScore();
	}

	public static boolean isPlayerDraw(User player, User dealer) {
		return dealer.isBlackjack() && player.isBlackjack();
	}

	public static boolean isPlayerLose(User player, User dealer) {
		return isDealerOnlyBlackjack(player, dealer)
			|| player.isBust()
			|| isDealerWinScore(player, dealer);
	}

	private static boolean isDealerOnlyBlackjack(User player, User dealer) {
		return dealer.isBlackjack() && !player.isBlackjack();
	}

	private static boolean isDealerWinScore(User player, User dealer) {
		return dealer.calculateScore() >= player.calculateScore();
	}
}
