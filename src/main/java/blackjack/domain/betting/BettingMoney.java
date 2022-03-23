package blackjack.domain.betting;

public class BettingMoney {

    private static final int MONEY_MINIMUM_THRESHOLD = 100;
    private static final int MONEY_BASIC_UNIT = 100;
    private static final int ZERO = 0;

    private static final String BETTING_MONEY_MINIMUM_ERROR_MESSAGE = "[ERROR] 배팅 금액에는 100미만의 값은 올 수 없습니다.";
    private static final String BETTING_MONEY_BASIC_UNIT_ERROR_MESSAGE = "[ERROR] 배팅 금액은 100원 단위여야 합니다.";

    private final long bettingMoney;

    public BettingMoney(int bettingMoney) {
        validateBettingMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public long getBettingMoney() {
        return bettingMoney;
    }

    private void validateBettingMoney(int bettingMoney) {
        validateMinimum(bettingMoney);
        validateBasicUnit(bettingMoney);
    }

    private void validateMinimum(int money) {
        if (money < MONEY_MINIMUM_THRESHOLD) {
            throw new IllegalArgumentException(BETTING_MONEY_MINIMUM_ERROR_MESSAGE);
        }
    }

    private void validateBasicUnit(int money) {
        if (money % MONEY_BASIC_UNIT != ZERO) {
            throw new IllegalArgumentException(BETTING_MONEY_BASIC_UNIT_ERROR_MESSAGE);
        }
    }

}
