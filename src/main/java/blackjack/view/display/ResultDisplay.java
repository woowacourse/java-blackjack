package blackjack.view.display;

import blackjack.model.Result;
import java.util.Arrays;

public enum ResultDisplay {
    WIN(Result.WIN, "승"),
    LOSE(Result.LOSE, "패"),
    DRAW(Result.DRAW, "무"),
    ;

    private final Result result;
    private final String value;

    ResultDisplay(final Result result, final String value) {
        this.result = result;
        this.value = value;
    }

    public static String getValue(final Result result) {
        return Arrays.stream(ResultDisplay.values())
                .filter(resultDisplay -> resultDisplay.result == result)
                .findFirst()
                .get()
                .value;
    }
}
