package blackjack.domain.betting;

public class BettingMoney {

    private static final String BETTING_MONEY_NEGATIVE_ERROR_MESSAGE = "[ERROR] 배팅 금액에는 0이하의 값은 올 수 없습니다.";
    private final int bettingMoney;

    public BettingMoney(int bettingMoney) {
        validateBettingMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    public int getBettingMoney() {
        return bettingMoney;
    }

    private void validateBettingMoney(int bettingMoney) {
        if (isNegativeOrZero(bettingMoney)) {
            throw new IllegalArgumentException(BETTING_MONEY_NEGATIVE_ERROR_MESSAGE);
        }
    }

    private boolean isNegativeOrZero(int money) {
        return money <= 0;
    }
}
