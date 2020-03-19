package model.user.money;

import exception.IllegalBettingMoneyFormatException;

public class BettingMoney {
    private static final int LOWER_MONEY_BOUND = 100;

    private final double bettingMoney;

    public BettingMoney(String input) {
        validate(input);
        this.bettingMoney = Double.parseDouble(input);
    }

    private void validate(String input) {
        validateFormat(input);
        validateRange(Double.parseDouble(input));
    }

    private void validateFormat(String input) {
        try {
            Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new IllegalBettingMoneyFormatException("배팅 금액은 100이상의 숫자만 입력 가능합니다.");
        }
    }

    private void validateRange(double input) {
        if (input < LOWER_MONEY_BOUND) {
            throw new IllegalBettingMoneyFormatException("배팅 금액은 "+LOWER_MONEY_BOUND+"이상의 숫자만 입력 가능합니다.");
        }
    }

    public double multiplyBettingMoney(double factor) {
        return bettingMoney * factor;
    }
}