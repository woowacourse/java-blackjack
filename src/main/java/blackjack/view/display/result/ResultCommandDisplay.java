package blackjack.view.display.result;

import blackjack.model.result.ResultCommand;
import java.util.Arrays;

public enum ResultCommandDisplay {
    WIN(ResultCommand.WIN, "승"),
    LOSE(ResultCommand.LOSE, "패"),
    DRAW(ResultCommand.DRAW, "무"),
    ;

    private final ResultCommand result;
    private final String value;

    ResultCommandDisplay(final ResultCommand result, final String value) {
        this.result = result;
        this.value = value;
    }

    public static String getValue(final ResultCommand result) {
        return Arrays.stream(ResultCommandDisplay.values())
                .filter(resultCommandDisplay -> resultCommandDisplay.result == result)
                .findFirst()
                .get()
                .value;
    }
}
