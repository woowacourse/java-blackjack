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

	public static double getMatchedResultType2(final Player player, final Dealer dealer) {
		if (player.isBust()) {
			return -1;
		}
		if (dealer.isBlackJack() && player.isBlackJack()){
			return 0;
		}
		if (dealer.isBlackJack()) {
			return -1;
		}
		if (player.isBlackJack()) {
			return 1.5;
		}
		if (dealer.compare(player) == 0) {
			return 0;
		}
		if (dealer.compare(player) > 0) {
			return -1;
		}
		return 1;
	}

	public String getValue() {
		return value;
	}
}
