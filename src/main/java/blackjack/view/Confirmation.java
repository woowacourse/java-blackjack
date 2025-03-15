package blackjack.view;

import java.util.Arrays;

public enum Confirmation {
    Y("y"),
    N("n");

    private final String sign;

    Confirmation(String sign) {
        this.sign = sign;
    }

    public static Confirmation find(String input) {
        return Arrays.stream(values())
                .filter(confirmation -> confirmation.sign.equals(input))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("y, n만 입력 가능합니다."));
    }
}
