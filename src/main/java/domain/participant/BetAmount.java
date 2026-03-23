package domain.participant;

import exception.BlackjackException;

public class BetAmount {

    public static final String INVALID_BET_AMOUNT_NUMBER = "배팅 금액은 숫자여야 합니다.";
    public static final String INVALID_BET_AMOUNT_POSITIVE = "배팅 금액은 양수여야 합니다.";
    public static final String INVALID_BET_AMOUNT_RANGE = "베팅 금액의 범위가 올바르지 않습니다. (1000 이상 3억 이하의 금액 입력)";
    private static final String NUMBER_FORMAT = "-?\\d+";
    private static final int MIN_BET_AMOUNT = 1000;
    private static final int MAX_BET_AMOUNT = 300_000_000;

    private final int betAmount;

    public BetAmount(String betAmount) {
        validate(betAmount);
        this.betAmount = Integer.parseInt(betAmount);
    }

    private void validate(String betAmount) {
        validateNumber(betAmount);
        validatePositive(betAmount);
        validateRange(betAmount);
    }

    private void validateNumber(String betAmount) {
        if (isNotNumber(betAmount)) {
            throw new BlackjackException(INVALID_BET_AMOUNT_NUMBER);
        }
    }

    private void validateRange(String betAmount) {
        if (isInvalidRange(betAmount)) {
            throw new BlackjackException(INVALID_BET_AMOUNT_RANGE);
        }
    }

    private void validatePositive(String betAmount) {
        if (isNotPositive(betAmount)) {
            throw new BlackjackException(INVALID_BET_AMOUNT_POSITIVE);
        }
    }

    private boolean isNotNumber(String betAmount) {
        return !betAmount.matches(NUMBER_FORMAT);
    }

    private boolean isNotPositive(String betAmount) {
        return Integer.parseInt(betAmount) <= 0;
    }

    private boolean isInvalidRange(String betAmount) {
        return Integer.parseInt(betAmount) < MIN_BET_AMOUNT || Integer.parseInt(betAmount) > MAX_BET_AMOUNT;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
