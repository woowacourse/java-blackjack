package blakcjack.domain.money;

import java.util.Collection;
import java.util.Objects;

public class Money {
	private final double money;

	public Money(final double money) {
		this.money = money;
	}

	public static Money calculateDealerProfitFrom(final Collection<Money> moneys) {
		final double dealerProfit = moneys.stream()
				.mapToDouble(amount -> -amount.money)
				.sum();
		return new Money(dealerProfit);
	}

	public Money calculateProfit(final float rate) {
		return new Money(money * rate);
	}

	public double getMoney() {
		return money;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Money money1 = (Money) o;
		return Double.compare(money1.money, money) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
