package domain;

public enum ResultType {
	WIN,
	LOSE,
	DRAW;

	public static ResultType generateResultType(Player player, Dealer dealer) {
		if (player.isBurst()) {
			return LOSE;
		}
		if (dealer.isEqaul(player)) {
			return DRAW;
		}
		if (dealer.isHigher(player)) {
			return LOSE;
		}
		return WIN;
	}
}
