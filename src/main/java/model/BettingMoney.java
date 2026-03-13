package model;

import exception.GameException;

public class BettingMoney {

    private static final String NUMBER_REGEX = "\\d+";
    private static final String NEGATIVE_REGEX = "-\\d+";
    private static final String ZERO_REGEX = "0+";
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
            throw new GameException("돈은 음수일 수 없습니다.");
        }
    }

    private void validateIsNumber(String bettingMoney) {
        if (!bettingMoney.matches(NUMBER_REGEX)) {
            throw new GameException("문자가 아닌 숫자를 입력해주세요.");
        }
    }

    private void validateNotOutOfRange(String bettingMoney) {
        if (bettingMoney.length() > LONG_MAX_VALUE.length()) {
            throw new GameException("입력 가능한 범위를 초과한 숫자입니다.");
        }
        if (bettingMoney.length() == LONG_MAX_VALUE.length()
                && bettingMoney.compareTo(LONG_MAX_VALUE) > 0) {
            throw new GameException("입력 가능한 범위를 초과한 숫자입니다.");
        }
    }

    private void validateNotZero(String bettingMoney) {
        if (bettingMoney.matches(ZERO_REGEX)) {
            throw new GameException("0원 이상을 입력해주세요.");
        }
    }
}
