package blackjack.domain;

public enum ResultType {
	WIN("승"),
	LOSE("패"),
	DRAW("무");

	private final String value;

	ResultType(String value) {
		this.value = value;
	}

	public static ResultType generateResultType(Player player, Dealer dealer) {
		if (player.isBurst() || dealer.isHigher(player)) {
			return LOSE;
		}
		if (dealer.isEqaul(player)) {
			return DRAW;
		}
		return WIN;
	}

	public String getValue() {
		return value;
	}
}
