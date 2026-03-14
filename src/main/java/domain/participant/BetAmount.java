package domain.participant;

public class BetAmount {

    private static final String INVALID_BET_AMOUNT_NUMBER = "배팅 금액은 숫자여야 합니다.";
    private static final String INVALID_BET_AMOUNT_POSITIVE = "배팅 금액은 양수여야 합니다.";
    private static final String NUMBER_FORMAT = "-?\\d+";

    private final int betAmount;

    public BetAmount(String betAmountInput) {
        validate(betAmountInput);
        this.betAmount = Integer.parseInt(betAmountInput);
    }

    private void validate(String betAmountInput) {
        validateNumber(betAmountInput);
        validatePositive(betAmountInput);
    }

    private void validateNumber(String betAmountInput) {
        if (isNotNumber(betAmountInput)) {
            throw new IllegalArgumentException(INVALID_BET_AMOUNT_NUMBER);
        }
    }

    private static boolean isNotNumber(String betAmountInput) {
        return !betAmountInput.matches(NUMBER_FORMAT);
    }

    private void validatePositive(String betAmountInput) {
        if (isNotPositive(betAmountInput)) {
            throw new IllegalArgumentException(INVALID_BET_AMOUNT_POSITIVE);
        }
    }

    private static boolean isNotPositive(String betAmountInput) {
        return Integer.parseInt(betAmountInput) <= 0;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
