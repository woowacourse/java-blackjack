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

	public static ResultType getMatchedResultType(final Player player, final Dealer dealer) {
		if (player.isBust()) {
			return LOSE;
		}
		if (dealer.isBlackJack() && player.isBlackJack()){
			return DRAW;
		}
		if (dealer.isBlackJack()) {
			return LOSE;
		}
		if (dealer.compare(player) == 0) {
			return DRAW;
		}
		if (dealer.compare(player) > 0) {
			return LOSE;
		}
		return WIN;
	}

	public String getValue() {
		return value;
	}
}
