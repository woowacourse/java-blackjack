package blackjack.view.mapper;

import blackjack.domain.game.Result2;
import java.util.Arrays;

public enum ResultStateMapper {

    WIN(Result2.WIN, "승"),
    LOSE(Result2.LOSE, "패"),
    TIE(Result2.TIE, "무"),
    ;

    private final Result2 result2;
    private final String symbol;

    ResultStateMapper(Result2 result2, String symbol) {
        this.result2 = result2;
        this.symbol = symbol;
    }

    public static String toSymbol(Result2 result2) {
        return Arrays.stream(values())
                .filter(it -> it.result2 == result2)
                .findFirst()
                .map(it -> it.symbol)
                .orElseThrow(IllegalArgumentException::new);
    }
}
