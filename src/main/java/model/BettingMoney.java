package model;

import exception.GameException;

public class BettingMoney {

    private static final String MONEY_IS_NOT_NUMBER = "문자가 아닌 숫자를 입력해주세요.";
    private static final String MONEY_IS_OUT_OF_RANGE = "입력 가능한 범위를 초과한 숫자입니다.";
    private static final String MONEY_IS_NEGATIVE = "돈은 음수일 수 없습니다.";
    private static final String MONEY_IS_ZERO = "0원 이상을 입력해주세요.";
    private static final String NUMBER_REGEX = "\\d+";
    private static final String NEGATIVE_REGEX = "-\\d+";
    private static final String LONG_MAX_VALUE = String.valueOf(Long.MAX_VALUE);

    private final long bettingMoney;

    public BettingMoney(String bettingMoney) {
        validate(bettingMoney);
        this.bettingMoney = parse(bettingMoney);
    }

    public long get() {
        return bettingMoney;
    }

    private long parse(String bettingMoney) {
        return Long.parseLong(bettingMoney);
    }

    private void validate(String bettingMoney) {
        validateNotNegative(bettingMoney);
        validateIsNumber(bettingMoney);
        validateNotOutOfRange(bettingMoney);
        validateNotZero(bettingMoney);
    }

    private void validateNotNegative(String bettingMoney) {
        if (bettingMoney.matches(NEGATIVE_REGEX)) {
            throw new GameException(MONEY_IS_NEGATIVE);
        }
    }

    private void validateIsNumber(String bettingMoney) {
        if (!bettingMoney.matches(NUMBER_REGEX)) {
            throw new GameException(MONEY_IS_NOT_NUMBER);
        }
    }

    private void validateNotOutOfRange(String bettingMoney) {
        if (bettingMoney.length() > LONG_MAX_VALUE.length()) {
            throw new GameException(MONEY_IS_OUT_OF_RANGE);
        }
        if (bettingMoney.length() == LONG_MAX_VALUE.length()
                && bettingMoney.compareTo(LONG_MAX_VALUE) > 0) {
            throw new GameException(MONEY_IS_OUT_OF_RANGE);
        }
    }

    private void validateNotZero(String bettingMoney) {
        if (bettingMoney.equals("0")) {
            throw new GameException(MONEY_IS_ZERO);
        }
    }
}
