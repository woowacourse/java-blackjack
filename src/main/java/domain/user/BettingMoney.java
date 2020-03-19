package domain.user;

public class BettingMoney {

    private static final String BETTING_MONEY_ERROR_MESSAGE = "배팅 가능한 금액이 아닙니다.";
    private int bettingMoney;

    BettingMoney(int bettingMoney) {
        validateMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateMoney(int bettingMoney) {
        if (bettingMoney <= 0 || bettingMoney > (int) ((int)Integer.MAX_VALUE/1.5)) {
            throw new IllegalArgumentException(BETTING_MONEY_ERROR_MESSAGE);
        }
    }
}
