package domain.betting;

public class FinalMoney {
	private final int money;

	public FinalMoney(int money) {
		this.money = money;
	}

	public int compare(Money money) {
		return money.calculateDifferent(this.money);
	}
}
