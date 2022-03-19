package blackjack.domain;

public class BettingAmount {

	private static final int MIN_VALUE = 10;
	private static final String LACK_OF_BETTING_AMOUNT_ERROR = "배팅 금액은 최소 10원이어야 합니다.";

	private final int initialValue;
	private int totalValue;

	public BettingAmount(final int value) {
		this(value, value);
	}

	public BettingAmount(final int totalValue, final int initialValue) {
		validateBettingAmount(initialValue);
		this.totalValue = totalValue;
		this.initialValue = initialValue;
	}

	private void validateBettingAmount(final int value) {
		if (value < MIN_VALUE) {
			throw new IllegalArgumentException(LACK_OF_BETTING_AMOUNT_ERROR);
		}
	}

	public void giveOneAndHalfTime() {
		totalValue = totalValue * 2 + totalValue / 2;
	}

	public void giveTwoTimes() {
		totalValue *= 2;
	}

	public void loseAll() {
		totalValue = 0;
	}

	public int calculateIncome() {
		return totalValue - initialValue;
	}

	public int getTotalValue() {
		return totalValue;
	}

	public int getInitialValue() {
		return initialValue;
	}
}
