package domain.result;

import domain.result.score.DealerFinalScore;
import domain.result.score.PlayerFinalScore;

public class Referee {

	public static boolean isPlayerWin(PlayerFinalScore playerScore, DealerFinalScore dealerScore) {
		return isPlayerOnlyBlackjack(playerScore, dealerScore)
			|| isDealerOnlyBust(playerScore, dealerScore)
			|| isPlayerScoreWin(playerScore, dealerScore);
	}

	private static boolean isPlayerOnlyBlackjack(PlayerFinalScore playerScore, DealerFinalScore dealerScore) {
		return playerScore.isBlackjack() && !dealerScore.isBlackjack();
	}

	private static boolean isDealerOnlyBust(PlayerFinalScore playerScore, DealerFinalScore dealerScore) {
		return dealerScore.isBust() && !playerScore.isBust();
	}

	private static boolean isPlayerScoreWin(PlayerFinalScore playerScore, DealerFinalScore dealerScore) {
		if (playerScore.isBust()) {
			return false;
		}
		return playerScore.isBigger(dealerScore);
	}

	public static boolean isPlayerDraw(PlayerFinalScore playerScore, DealerFinalScore dealerScore) {
		return playerScore.isBlackjack() && dealerScore.isBlackjack();
	}

	public static boolean isPlayerLose(PlayerFinalScore playerScore, DealerFinalScore dealerScore) {
		return isDealerOnlyBlackjack(playerScore, dealerScore)
			|| playerScore.isBust()
			|| isDealerWinScore(playerScore, dealerScore);
	}

	private static boolean isDealerOnlyBlackjack(PlayerFinalScore playerScore, DealerFinalScore dealerScore) {
		return dealerScore.isBlackjack() && !playerScore.isBlackjack();
	}

	private static boolean isDealerWinScore(PlayerFinalScore playerScore, DealerFinalScore dealerScore) {
		return dealerScore.isBigger(playerScore);
	}
}
