package blackjack.domain.gamer;

public record Money(int amount) {

	private static final int MINIMUM_AMOUNT = 0;

	public Money {
		if (amount < MINIMUM_AMOUNT) {
			throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
		}
	}

	public Money add(Money money) {
		return new Money(amount + money.amount());
	}

	public Money multiplyByRatio(double ratio) {
		return new Money((int)(amount * ratio));
	}
}
