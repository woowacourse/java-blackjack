package blackjack.domain.user;

public enum Result {
	BLACKJACK_WIN(1.5, "승"),
	WIN(1, "승"),
	LOSE(-1, "패"),
	DRAW(0, "무");

	private final double multiple;
	private final String result;

	Result(double multiple, String result) {
		this.multiple = multiple;
		this.result = result;
	}

	public static Result getPlayerResultByDealer(Playable player, Playable dealer) {
		if (player.isBlackjack()) {
			return getPlayerResultByDealerIfPlayerIsBlackJack(dealer);
		}
		return getPlayerResultByDealerIfPlayerIsNotBlackJack(player, dealer);
	}

	private static Result getPlayerResultByDealerIfPlayerIsBlackJack(Playable dealer) {
		if (dealer.isBlackjack()) {
			return DRAW;
		}
		return BLACKJACK_WIN;
	}

	private static Result getPlayerResultByDealerIfPlayerIsNotBlackJack(Playable player, Playable dealer) {
		if (player.isWinner(dealer.computeScore())) {
			return WIN;
		}
		if (player.isLoser(dealer.computeScore())) {
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
