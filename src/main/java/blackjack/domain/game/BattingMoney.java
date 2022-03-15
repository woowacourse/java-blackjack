package blackjack.domain.game;

import java.util.regex.Pattern;

public class BattingMoney {
    private static final Pattern NATURAL_NUMBER_PATTERN = Pattern.compile("^[1-9][0-9]*$");
    private final int value;

    public BattingMoney(final String value) {
        validateNaturalNumber(value);
        this.value = Integer.parseInt(value);
    }

    private void validateNaturalNumber(final String value) {
        if (!NATURAL_NUMBER_PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("배팅 금액은 자연수여야 합니다.");
        }
    }
}
