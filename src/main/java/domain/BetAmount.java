package domain;

import java.util.regex.Pattern;

public class BetAmount {

    public static final int MIN_BETTING_AMOUNT = 10000;

    private final int betAmount;

    public BetAmount(String value) {
        validate(value);
        this.betAmount = Integer.parseInt(value);
    }

    private void validate(String value) {
        validateDigit(value);
        validateMinBettingAmount(value);
    }

    private void validateDigit(String value) {
        if (!Pattern.matches("\\d+", value)) {
            throw new IllegalArgumentException("[ERROR] 숫자만 입력해주세요.");
        }
    }

    private void validateMinBettingAmount(String value) {
        if (Integer.parseInt(value) < MIN_BETTING_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 10000원 이상 베팅해주세요.");
        }
    }
}
