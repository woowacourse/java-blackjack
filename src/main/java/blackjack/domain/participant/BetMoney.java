package blackjack.domain.participant;

public class BetMoney {

	public static final int UNIT = 1000;
	private static final String MIN_BET_MONEY_ERROR = "베팅 금액은 1000원 이상이어야 합니다.";
	private static final String UNIT_BET_MONEY_ERROR = "베팅 금액은 1000원 단위로만 가능합니다.";

	private final int value;

	public BetMoney(int betMoney) {
		validate(betMoney);
		this.value = betMoney;
	}

	public BetMoney() {
		this(UNIT);
	}

	private void validate(int betMoney) {
		if (betMoney < UNIT) {
			throw new IllegalArgumentException(MIN_BET_MONEY_ERROR);
		}
		if (betMoney % UNIT != 0) {
			throw new IllegalArgumentException(UNIT_BET_MONEY_ERROR);
		}
	}
}
