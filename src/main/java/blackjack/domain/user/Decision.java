package blackjack.domain.user;

import java.util.Arrays;

public enum Decision {
    Y("Y", "y", true),
    N("N", "n", false);

    private static final String INVALID_INPUT= "올바르지 않은 입력입니다.";

    private String uppercase;
    private String lowercase;
    private boolean intended;

    Decision(String uppercase, String lowercase, boolean intended) {
        this.uppercase = uppercase;
        this.lowercase = lowercase;
        this.intended = intended;
    }

    public static boolean chosenBy(String decision) {
        return Arrays.stream(values())
                .filter(x -> x.uppercase.equals(decision) ||
                        x.lowercase.equals(decision))
                .findFirst()
                .map(a -> a.intended)
                .orElseThrow(() -> new IllegalArgumentException(INVALID_INPUT));
    }
}
