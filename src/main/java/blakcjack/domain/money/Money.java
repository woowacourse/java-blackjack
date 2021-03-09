package blakcjack.domain.money;

import blakcjack.domain.outcome.Outcome;

import java.util.Collection;
import java.util.Objects;

public class Money {
	private final float money;

	public Money(final float money) {
		this.money = money;
	}

	public Money calculateProfit(final Outcome outcome) {
		return new Money(money * outcome.getEarningRate());
	}

	public static Money calculateDealerProfitFrom(final Collection<Money> moneys) {
		final float dealerProfit = (float) moneys.stream()
				.mapToDouble(amount -> -amount.money)
				.sum();
		return new Money(dealerProfit);
	}

	public float getMoney() {
		return money;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Money money1 = (Money) o;
		return Float.compare(money1.money, money) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(money);
	}
}
