package domain;

import exception.BlackjackException;
import exception.ExceptionMessage;

public class BettingMoney {
    private int money;

    public BettingMoney(int money) {
        validateMoney(money);
        this.money = money;
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
