package blackjack.domain.gamer;

public record Money(int amount) {

	public Money add(Money money) {
		return new Money(amount + money.amount());
	}

	public Money multiplyByRatio(double ratio) {
		return new Money((int)(amount * ratio));
	}
}
