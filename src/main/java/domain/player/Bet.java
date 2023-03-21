package domain.player;

public class Bet {

	private final static int MIN_BET = 0;
	private final static int MAX_BET = 10_000;
	private final static String BET_INVALID_AMOUNT_ERROR_MESSAGE = "베팅 금액은 0 이상 10000 이하여야 합니다.";

	private final int bet;

	public Bet(final int bet) {
		validate(bet);
		this.bet = bet;
	}

	private void validate(final int bet) {
		if (bet < MIN_BET || bet > MAX_BET) {
			throw new IllegalArgumentException(BET_INVALID_AMOUNT_ERROR_MESSAGE);
		}
	}

	public int getBet() {
		return bet;
	}
}
