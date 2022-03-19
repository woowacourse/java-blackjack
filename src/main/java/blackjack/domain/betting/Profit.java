package blackjack.domain.betting;

import blackjack.domain.process.Match;
import java.util.Objects;

public class Profit {
	private static final double PROFIT_RATIO = 1.5;
	private static final int DECREASE_RATIO = -1;

	private final int bettingMoney;
	private final Match match;

	private Profit(Match match, int bettingMoney) {
		this.match = match;
		this.bettingMoney = bettingMoney;
	}

	public static Profit of(Match match, int bettingMoney) {
		return new Profit(match, bettingMoney);
	}

	public int calculateResult() {
		if (this.match == Match.DRAW) {
			return 0;
		}
		if (this.match == Match.LOSE) {
			return DECREASE_RATIO * this.bettingMoney;
		}
		if (this.match == Match.BLACKJACK_WIN) {
			return (int) (PROFIT_RATIO * this.bettingMoney);
		}
		return this.bettingMoney;
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
		return bettingMoney == profit.bettingMoney && match == profit.match;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bettingMoney, match);
	}
}
