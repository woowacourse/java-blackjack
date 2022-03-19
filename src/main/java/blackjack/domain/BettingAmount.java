package blackjack.domain;

public class BettingAmount {

	private static final int MIN_VALUE = 10;
	private static final String LACK_OF_BETTING_AMOUNT_ERROR = "배팅 금액은 최소 10원이어야 합니다.";

	private final int initialValue;
	private int finalValue;

	public BettingAmount(final int value) {
		this(value, value);
	}

	public BettingAmount(final int finalValue, final int initialValue) {
		validateBettingAmount(initialValue);
		this.finalValue = finalValue;
		this.initialValue = initialValue;
	}

	private void validateBettingAmount(final int value) {
		if (value < MIN_VALUE) {
			throw new IllegalArgumentException(LACK_OF_BETTING_AMOUNT_ERROR);
		}
	}

	public void giveOneAndHalfTime() {
		finalValue = finalValue + finalValue / 2;
	}

	public void giveTwoTimes() {
		finalValue *= 2;
	}

	public void loseAll() {
		finalValue = 0;
	}

	public int calculateIncome() {
		return finalValue - initialValue;
	}

	public int getFinalValue() {
		return finalValue;
	}

	public int getInitialValue() {
		return initialValue;
	}
}
