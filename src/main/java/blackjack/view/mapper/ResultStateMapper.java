package blackjack.view.mapper;

import blackjack.domain.profit.Result;
import java.util.Arrays;

public enum ResultStateMapper {

    WIN(Result.WIN, "승"),
    LOSE(Result.LOSE, "패"),
    TIE(Result.TIE, "무"),
    ;

    private final Result result;
    private final String symbol;

    ResultStateMapper(Result result, String symbol) {
        this.result = result;
        this.symbol = symbol;
    }

    public static String toSymbol(Result result) {
        return Arrays.stream(values())
                .filter(it -> it.result == result)
                .findFirst()
                .map(it -> it.symbol)
                .orElseThrow(IllegalArgumentException::new);
    }
}
