package blackjack.domain.betting;

import blackjack.domain.process.Match;
import java.util.Objects;

public class Profit {
	private static final double PROFIT_RATIO = 1.5;
	private static final int DECREASE_RATIO = -1;

	private final int prizeValue;

	private Profit(int prizeValue) {
		this.prizeValue = prizeValue;
	}

	public static Profit of(Match match, int money) {
		if (match == Match.DRAW) {
			return new Profit(0);
		}
		if (match == Match.LOSE) {
			return new Profit(money * DECREASE_RATIO);
		}
		if (match == Match.BLACKJACK_WIN) {
			return new Profit((int)(money * PROFIT_RATIO));
		}
		return new Profit(money);
	}

	public int getPrizeValue() {
		return prizeValue;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Profit profit = (Profit) o;
		return prizeValue == profit.prizeValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(prizeValue);
	}
}
