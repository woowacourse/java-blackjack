package balckjack.domain;

import java.util.List;
import java.util.stream.Collectors;

class CourtCard extends Card {

    private static final List<String> symbols = List.of("J", "Q", "K");

    public static List<CourtCard> createCourtCards(Pattern pattern) {
        return symbols.stream().map((symbol) -> new CourtCard(pattern, symbol))
            .collect(Collectors.toList());
    }

    public CourtCard(Pattern pattern, String symbol) {
        super(pattern, symbol);
    }

    @Override
    protected void validateSymbol(String symbol) {
        if (!symbols.contains(symbol)) {
            throw new IllegalArgumentException(
                String.format("심볼은 J, Q, K 중 하나여야 합니다. 입력된 값 : %s", symbol));
        }
    }

    @Override
    protected int getValue() {
        return 10;
    }
}
