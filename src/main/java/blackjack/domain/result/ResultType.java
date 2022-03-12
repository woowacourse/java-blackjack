package blackjack.domain.result;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;

public enum ResultType {
	WIN("승"),
	LOSE("패"),
	DRAW("무");

	private final String value;

	ResultType(final String value) {
		this.value = value;
	}

	public static ResultType generateResultType(final Player player, final Dealer dealer) {
		if (player.isBurst()) {
			return LOSE;
		}
		if (dealer.hasEqualScore(player)) {
			return DRAW;
		}
		if (dealer.hasHigherScore(player)) {
			return LOSE;
		}
		return WIN;
	}

	public String getValue() {
		return value;
	}
}
