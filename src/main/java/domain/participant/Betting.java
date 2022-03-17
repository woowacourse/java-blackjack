package domain.participant;

public class Betting {
	private static final String BETTING_MONEY_NEGATIVE_ERROR_MESSAGE = "[Error] 배팅 금약은 0원 이상이어야 합니다.";
	private final int bettingMoney;

	public Betting(int money) {
		validatePositiveNumber(money);
		bettingMoney = money;
	}

	private void validatePositiveNumber(int money) {
		if (money < 0) {
			throw new IllegalArgumentException(BETTING_MONEY_NEGATIVE_ERROR_MESSAGE);
		}
	}

	public int getBettingMoney() {
		return bettingMoney;
	}
}
