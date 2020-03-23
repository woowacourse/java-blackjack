package domain.user;

public class BettingMoney {

    private static final String BETTING_MONEY_ERROR_MESSAGE = "배팅 가능한 금액이 아닙니다.";
    private final double bettingMoney;

    BettingMoney(int bettingMoney) {
        validateMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateMoney(double bettingMoney) {
        if (bettingMoney <= 0 || bettingMoney >Integer.MAX_VALUE/1.5) {
            throw new IllegalArgumentException(BETTING_MONEY_ERROR_MESSAGE);
        }
    }

    double getBettingMoney() {
        return this.bettingMoney;
    }
}
