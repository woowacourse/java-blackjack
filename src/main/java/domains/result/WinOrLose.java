package domains.result;

import domains.user.Dealer;
import domains.user.Player;

public enum WinOrLose {
	WIN("승"),
	DRAW("무"),
	LOSE("패");

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

	public static WinOrLose oppose(WinOrLose outcome) {
		if (WIN.equals(outcome)) {
			return LOSE;
		}
		if (LOSE.equals(outcome)) {
			return WIN;
		}
		return DRAW;
	}

	public String getWinOrLose() {
		return winOrLose;
	}
}
