package blackjack.domain;

import java.util.Arrays;

public enum YesOrNo {
    YES('y'),
    NO('n');

    private final char value;

    YesOrNo(char value) {
        this.value = value;
    }

    static YesOrNo of(String input) {
        if (input == null || input.length() != 1) {
            throw new IllegalArgumentException("이상한 수");
        }
        return Arrays.stream(YesOrNo.values())
            .filter(yesOrNo -> yesOrNo.value == input.toLowerCase().charAt(0))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("이상한 수"));
    }
}
