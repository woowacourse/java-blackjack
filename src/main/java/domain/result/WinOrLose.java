package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;

public enum WinOrLose {
	WIN(1),
	DRAW(0),
	LOSE(-1),
	BLACK_JACK_WIN(1.5);

	private final double earningRate;

	WinOrLose(double earningRate) {
		this.earningRate = earningRate;
	}

	public static WinOrLose judgePlayerWinOrLose(Dealer dealer, Player player) {
		if (dealer.isBlackJack() || player.isBlackJack()) {
			return judgeWinOrLoseAtBlackJackExist(dealer, player);
		}

		if (dealer.isBust() || player.isBust()) {
			return judgeWinOrLoseAtBustExist(player);
		}
		return judgeWinOrLoseByScore(dealer, player);
	}

	private static WinOrLose judgeWinOrLoseAtBlackJackExist(Dealer dealer, Player player) {
		if (dealer.isBlackJack()) {
			return judgeWinOrLoseAtDealerBlackJack(player);
		}
		return WinOrLose.BLACK_JACK_WIN;
	}

	private static WinOrLose judgeWinOrLoseAtDealerBlackJack(Player player) {
		if (player.isBlackJack()) {
			return WinOrLose.DRAW;
		}
		return WinOrLose.LOSE;
	}

	private static WinOrLose judgeWinOrLoseAtBustExist(Player player) {
		if (player.isBust()) {
			return WinOrLose.LOSE;
		}

		return WinOrLose.WIN;
	}

	private static WinOrLose judgeWinOrLoseByScore(Dealer dealer, Player player) {
		int playerScore = player.getScore();
		int dealerScore = dealer.getScore();

		if (playerScore > dealerScore) {
			return WinOrLose.WIN;
		}

		if (playerScore < dealerScore) {
			return WinOrLose.LOSE;
		}
		return WinOrLose.DRAW;
	}

	public double getEarningRate() {
		return earningRate;
	}
}
