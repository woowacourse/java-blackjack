package blackjack.domain;

import java.util.regex.Pattern;

public class BattingAmount {
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^[1-9][0-9]*$");

    private final int value;

    public BattingAmount(String value) {
        validate(value);
        this.value = Integer.parseInt(value);
    }

    private void validate(String value) {
        if (!NUMERIC_PATTERN.matcher(value).matches() || Integer.parseInt(value) <= 0) {
            throw new IllegalArgumentException("양수만 입력 가능합니다.");
        }
    }
}
