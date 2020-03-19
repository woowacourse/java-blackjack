package domain;

public class Profit {
	private static final double BLACKJACK_WIN_RATE = 1.5;
	private static final double WIN_RATE = 1;
	private static final double DRAW_RATE = 0;
	private static final double LOSE_RATE = -1;

	private final double profit;

	private Profit(double profit) {
		this.profit = profit;
	}

	public static Profit of(User source, User target, BettingMoney bettingMoney) {
		if (source.isWin(target) && source.isBlackJack()) {
			return new Profit(bettingMoney.getValue() * BLACKJACK_WIN_RATE);
		}
		if (source.isWin(target)) {
			return new Profit(bettingMoney.getValue() * WIN_RATE);
		}
		if (source.isDraw(target)) {
			return new Profit(bettingMoney.getValue() * DRAW_RATE);
		}
		return new Profit(bettingMoney.getValue() * LOSE_RATE);
	}

	public double getValue() {
		return profit;
	}
}
