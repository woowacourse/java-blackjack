package blackjack.domain.participant;

public class Money {
	private final double money;

	public Money(double money) {
		this.money = money;
	}

	public static Money of(double money) {
		return new Money(money);
	}

	public double calculateMoneyWithProfit(double profit) {
		return money * profit;
	}

	public int getMoney() {
		return (int)money;
	}
}
