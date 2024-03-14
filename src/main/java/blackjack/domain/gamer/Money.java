package blackjack.domain.gamer;

public record Money(int amount) {

	private static final Money ZERO_AMOUNT = new Money(0);

	public Money add(Money money) {
		return new Money(amount + money.amount());
	}

	public static Money getZeroAmountMoney() {
		return ZERO_AMOUNT;
	}

	public Money multiplyByRatio(double ratio) {
		return new Money((int)(amount * ratio));
	}
}
