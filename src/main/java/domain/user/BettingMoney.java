package domain.user;

public class BettingMoney {
	private double bettingMoney;

	public BettingMoney(String inputBettingMoney) {
		double bettingMoney = Double.parseDouble(inputBettingMoney);
		validate(bettingMoney);
		this.bettingMoney = bettingMoney;
	}

	public BettingMoney(double bettingMoney) {
		validate(bettingMoney);
		this.bettingMoney = bettingMoney;
	}

	private void validate(double bettingMoney) {
		if (bettingMoney < 1) {
			throw new IllegalArgumentException("베팅 금액은 1원 이상이여야합니다.");
		}
	}
}
