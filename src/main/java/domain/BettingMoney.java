package domain;

public class BettingMoney {
	private static final int MIN = 0;

	private final int bettingMoney;

	public BettingMoney(int bettingMoney) {
		validate(bettingMoney);
		this.bettingMoney = bettingMoney;
	}

	private void validate(int bettingMoney) {
		if (bettingMoney < MIN) {
			throw new IllegalArgumentException("배팅 금액은 0 이상이어야 합니다.");
		}
	}

	public static BettingMoney from(String bettingMoney) {
		return new BettingMoney(Integer.parseInt(bettingMoney));
	}

	public int getValue() {
		return bettingMoney;
	}
}
