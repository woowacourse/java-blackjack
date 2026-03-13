package domain;

import exception.BlackjackException;
import exception.ExceptionMessage;

public class BettingMoney {
    private final int money;

    public BettingMoney(int money) {
        validateMoney(money);
        validateMoneyUnit(money);
        this.money = money;
    }

    private void validateMoneyUnit(int money) {
        if (money % 100 != 0) {
            throw new BlackjackException(ExceptionMessage.MONEY_UNIT_ERROR);
        }
    }

    private void validateMoney(int money) {
        if (money < 1000) {
            throw new BlackjackException(ExceptionMessage.MONEY_INPUT_ERROR);
        }
    }

    public int getMoney() {
        return this.money;
    }
}
