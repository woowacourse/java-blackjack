package blackjack.domain;

import blackjack.utils.Validator;

import java.util.Arrays;

public enum HitOption {

    YES("y", true),
    NO("n", false)
    ;

    private static final String HIT_OPTION_MESSAGE = "y, n 중에서 입력해주세요.";

    private final String symbol;
    private final boolean isGiven;

    HitOption(final String symbol, final boolean isGiven) {
        this.symbol = symbol;
        this.isGiven = isGiven;
    }

    public static boolean hits(final String symbol) {
        Validator.validateNullOrEmpty(symbol);
        return Arrays.stream(HitOption.values())
                .filter(answer -> symbol.equalsIgnoreCase(answer.symbol))
                .findFirst()
                .map(answer -> answer.isGiven)
                .orElseThrow(() -> new IllegalArgumentException(HIT_OPTION_MESSAGE));
    }
}
