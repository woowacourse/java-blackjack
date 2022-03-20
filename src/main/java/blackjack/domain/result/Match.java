package blackjack.domain.result;

import java.util.Arrays;

public enum Match {
    WIN(1, "승"),
    LOSE(-1, "패"),
    DRAW(0, "무")
    ;

    public static final String NO_MATCH_RESULT_ERROR_MESSAGE = "결과가 존재하지 않습니다.";

    private final int number;
    private final String result;

    Match(int number, String result) {
        this.number = number;
        this.result = result;
    }

    public static Match of(int number) {
        return Arrays.stream(values())
                .filter(value -> value.number == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(NO_MATCH_RESULT_ERROR_MESSAGE));
    }

    public String getResult() {
        return result;
    }
}
