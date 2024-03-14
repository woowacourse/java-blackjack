package blackjack.domain.gamer;

public record Money(int amount) {

	private static final Money ZERO_AMOUNT = new Money(0);

	public static Money getZeroAmountMoney() {
		return ZERO_AMOUNT;
	}

	public Money add(Money money) {
		return new Money(amount + money.amount());
	}

	public Money multiplyByRatio(double ratio) {
		return new Money((int)(amount * ratio));
	}
}
