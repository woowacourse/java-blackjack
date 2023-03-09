package domain;

public class BetMoney {
    private static final int BET_UNIT = 1_000;
    private static final String ERROR_BET_UNIT = "[ERROR] 배팅 금액은 1,000원 단위만 가능합니다";

    private final int money;

    public BetMoney(int money) {
        validate(money);
        this.money = money;
    }

    private void validate(int money) {
        if (money % BET_UNIT != 0) {
            throw new IllegalArgumentException(ERROR_BET_UNIT);
        }
    }
}
