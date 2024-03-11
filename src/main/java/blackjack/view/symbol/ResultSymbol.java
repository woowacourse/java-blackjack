package blackjack.view.symbol;

import blackjack.model.results.Result;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum ResultSymbol {
    WIN("승"),
    PUSH("무"),
    LOSE("패");

    private final String symbol;

    ResultSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static String convertToSymbol(Result result) {
        return Arrays.stream(values())
                .filter(resultSymbol -> resultSymbol.name().equals(result.name()))
                .findFirst()
                .map(ResultSymbol::getSymbol)
                .orElseThrow(() -> new NoSuchElementException("올바르지 않은 결과 값입니다."));
    }

    public String getSymbol() {
        return symbol;
    }
}
