package blackjack.domain;

public enum BlackjackConstants {
	BLACKJACK_VALUE(21),
	DEALER_MINIMUM_VALUE(17),
	INITIAL_CARD_COUNT(2);

	private final int value;

	BlackjackConstants(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
