package balckjack.domain;

import java.util.List;

public class CourtCard extends Card {

    private static final List<String> symbols = List.of("J", "Q", "K");

    public CourtCard(String symbol) {
        super(symbol, 10);
    }

    @Override
    protected void validateSymbol(String symbol) {
        if (!symbols.contains(symbol)) {
            throw new IllegalArgumentException(
                String.format("심볼은 J, Q, K 중 하나여야 합니다. 입력된 값 : %s", symbol));
        }
    }
}
