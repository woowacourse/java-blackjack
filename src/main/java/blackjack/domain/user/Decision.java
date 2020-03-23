package blackjack.domain.user;

import java.util.Arrays;

public enum Decision {
    HIT("y", true),
    STAND("n", false);

    private static final String INVALID_INPUT= "올바르지 않은 입력입니다.";

    private String character;
    private boolean intended;

    Decision(String character, boolean intended) {
        this.character = character;
        this.intended = intended;
    }

    public static boolean chosenBy(String decision) {
        return Arrays.stream(values())
                .filter(x -> x.character.equals(decision.toLowerCase()))
                .findFirst()
                .map(a -> a.intended)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT));
    }
}
