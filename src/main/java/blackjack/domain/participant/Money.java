package blackjack.domain.participant;

public class Money {
	private double money;

	public Money(double money) {
		this.money = money;
	}

	public static Money of(double money) {
		return new Money(money);
	}

	public double calculateMoneyWithProfit(double profit) {
		return money * profit;
	}

	public double getMoney() {
		return money;
	}
}
