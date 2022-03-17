package blackjack.domain;

public class BattingAmount {

	private static final int MIN_VALUE = 10;
	private static final String LACK_OF_BATTING_AMOUNT_ERROR = "배팅 금액은 최소 10원이어야 합니다.";

	private final int initialValue;
	private int finalValue;

	public BattingAmount(final int value) {
		this(value, value);
	}

	public BattingAmount(final int finalValue, final int initialValue) {
		validateBattingAmount(initialValue);
		this.finalValue = finalValue;
		this.initialValue = initialValue;
	}

	private void validateBattingAmount(final int value) {
		if (value < MIN_VALUE) {
			throw new IllegalArgumentException(LACK_OF_BATTING_AMOUNT_ERROR);
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
