package blackjack.domain;

public class BattingAmount {

	private static final int MIN_VALUE = 10;
	private static final String LACK_OF_BATTING_AMOUNT_ERROR = "배팅 금액은 최소 10원이어야 합니다.";

	private int value;

	public BattingAmount(final int value) {
		validateBattingAmount(value);
		this.value = value;
	}

	private void validateBattingAmount(int value) {
		if (value < MIN_VALUE) {
			throw new IllegalArgumentException(LACK_OF_BATTING_AMOUNT_ERROR);
		}
	}

	public int getValue() {
		return value;
	}

	public void giveOneAndHalfTime() {
		value = value + value / 2;
	}

	public void loseAll() {
		value = 0;
	}
}
