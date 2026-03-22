package domain.participant;

public class Money {
    public static final int MINIMUM_BET_AMOUNT = 1_000;
    public static final int MAXIMUM_BET_AMOUNT = 300_000;
    public static final int MONEY_DIVIDE_UNIT = 10;

    private final int betAmount;

    public Money(int betAmount) {
        validate(betAmount);
        this.betAmount = betAmount;
    }

    private void validate(int betAmount) {
        validateRange(betAmount);
        validateUnit(betAmount);
    }

    private void validateRange(int betAmount) {
        if (betAmount < MINIMUM_BET_AMOUNT || betAmount > MAXIMUM_BET_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 1,000원 이상 300,000원 이하로 입력해주세요.");
        }
    }

    private void validateUnit(int betAmount) {
        if (betAmount % MONEY_DIVIDE_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 10원 단위이어야 합니다.");
        }
    }

    public int getBetAmount() {
        return betAmount;
    }
}
