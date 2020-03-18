package blackjack.domain.user;

import java.util.Arrays;

public enum Decision {
    Y("Y", "y", true),
    N("N", "n", false);

    private String uppercase;
    private String lowercase;
    private boolean intended;

    Decision(String uppercase, String lowercase, boolean intended) {
        this.uppercase = uppercase;
        this.lowercase = lowercase;
        this.intended = intended;
    }

    public static boolean chosenBy(String answer) {
        return Arrays.stream(values())
                .filter(x -> x.uppercase.equals(answer) ||
                        x.lowercase.equals(answer))
                .findFirst()
                .map(a -> a.intended)
                .orElseThrow(IllegalArgumentException::new);
    }
}
