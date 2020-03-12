package blackjack.view;

import java.util.Arrays;

public enum Answer {
    Y("Y", "y", true),
    N("N", "n", false);


    private String uppercase;
    private String lowercase;
    private boolean intended;

    Answer(String uppercase, String lowercase, boolean intended) {
        this.uppercase = uppercase;
        this.lowercase = lowercase;
        this.intended = intended;
    }

    public static boolean of(String answer) {
        return Arrays.stream(values())
                .filter(x -> x.uppercase.equals(answer) ||
                        x.lowercase.equals(answer))
                .findFirst()
                .map(a -> a.intended)
                .orElseThrow(() -> new IllegalArgumentException("다시 입력해 주세요"));
    }
}
