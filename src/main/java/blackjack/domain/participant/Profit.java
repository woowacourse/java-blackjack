package blackjack.domain.participant;

public class Profit {
	private double money;

	public Profit(double money) {
		this.money = money;
	}

	public static Profit of(double money) {
		return new Profit(money);
	}

	public double calculateMoneyWithProfit(double profit) {
		return money * profit;
	}

	public double getMoney() {
		return money;
	}
}
