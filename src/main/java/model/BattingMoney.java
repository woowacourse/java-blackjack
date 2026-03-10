package model;

import constant.MoneyErrorCode;
import exception.GameException;

public class BattingMoney {

    private final Integer battingMoney;

    public BattingMoney(String battingMoney) {
        int parsed = parse(battingMoney);
        validate(parsed);
        this.battingMoney = parsed;
    }

    public Integer get() {
        return battingMoney;
    }

    private int parse(String battingMoney) {
        try {
            return Integer.parseInt(battingMoney);
        } catch (NumberFormatException e) {
            throw new GameException(MoneyErrorCode.MONEY_IS_NOT_NUMBER);
        }
    }

    private void validate(int battingMoney) {
        validateBattingMoneyIsNotNegative(battingMoney);
        validateBattingMoneyIsNotZero(battingMoney);
    }

    private void validateBattingMoneyIsNotNegative(int battingMoney) {
        if (battingMoney < 0) {
            throw new GameException(MoneyErrorCode.MONEY_IS_NEGATIVE);
        }
    }

    private void validateBattingMoneyIsNotZero(int battingMoney) {
        if (battingMoney == 0) {
            throw new GameException(MoneyErrorCode.MONEY_IS_ZERO);
        }
    }
}
