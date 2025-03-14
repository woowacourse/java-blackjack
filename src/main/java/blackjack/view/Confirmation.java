package blackjack.view;

import blackjack.common.ErrorMessage;
import java.util.Arrays;

public enum Confirmation {
    Y("y"),
    N("n"),
    ;

    private final String sign;

    Confirmation(String sign) {
        this.sign = sign;
    }

    public static Confirmation find(String input) {
        return Arrays.stream(values())
                .filter(confirmation -> confirmation.sign.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_CONFIRMATION_INPUT.getMessage()));
    }
}
