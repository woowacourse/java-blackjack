package blackjack.domain.result;

import blackjack.domain.card.Hand;
import blackjack.domain.user.Playable;

public enum ResultType {
	BLACKJACK_WIN(1.5, "승"),
	WIN(1, "승"),
	LOSE(-1, "패"),
	DRAW(0, "무");

	private final double multiple;
	private final String result;

	ResultType(double multiple, String result) {
		this.multiple = multiple;
		this.result = result;
	}

	public static ResultType getPlayerResultByDealer(Hand player, Hand dealer) {
		if (player.isBlackjack()) {
			return getPlayerResultByDealerIfPlayerIsBlackJack(dealer);
		}
		return getPlayerResultByDealerIfPlayerIsNotBlackJack(player, dealer);
	}

	private static ResultType getPlayerResultByDealerIfPlayerIsBlackJack(Hand dealer) {
		if (dealer.isBlackjack()) {
			return DRAW;
		}
		return BLACKJACK_WIN;
	}

	private static ResultType getPlayerResultByDealerIfPlayerIsNotBlackJack(Hand player, Hand dealer) {
		if (player.isWin(dealer)) {
			return WIN;
		}
		if (player.isLose(dealer)) {
			return LOSE;
		}
		return DRAW;
	}

	public double computeResultAmount(double amount) {
		return amount * multiple;
	}

	public boolean isWinOrBlackjackWin() {
		return this == BLACKJACK_WIN || this == WIN;
	}

	public boolean isLose() {
		return this == LOSE;
	}

	public boolean isDraw() {
		return this == DRAW;
	}
}
