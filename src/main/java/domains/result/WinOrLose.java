package domains.result;

import domains.user.Dealer;
import domains.user.Player;

public enum WinOrLose {
	WIN("승"), LOSE("패"), DRAW("무");

	private String winOrLose;

	WinOrLose(String winOrLose) {
		this.winOrLose = winOrLose;
	}

	public static WinOrLose checkWinOrLose(Player player, Dealer dealer) {
		if (player.score() > dealer.score()) {
			return WIN;
		}
		if (player.score() < dealer.score()) {
			return LOSE;
		}
		return DRAW;
	}

	public String getWinOrLose() {
		return winOrLose;
	}
}
