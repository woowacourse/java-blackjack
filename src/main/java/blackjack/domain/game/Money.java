package blackjack.domain.game;

public class Money {

	private final int value;

	public Money(int money) {
		this.value = money;
	}

	public Money increase(Money money) {
		return new Money(this.value + money.value);
	}

	public Money decrease(Money money) {
		return new Money(this.value - money.value);
	}

	public int getValue() {
		return value;
	}
}
