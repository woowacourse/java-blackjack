package domain.result;

import java.util.Objects;

public class Profit {
	public static final Profit ZERO = new Profit(0);
	private final double profit;

	public Profit(double profit) {
		this.profit = profit;
	}

	public Profit plus(Profit other) {
		return new Profit(this.profit + other.profit);
	}

	public Profit negative() {
		return new Profit(this.profit * -1);
	}

	public double getProfit() {
		return profit;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Profit profit1 = (Profit) o;
		return Double.compare(profit1.profit, profit) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(profit);
	}
}
