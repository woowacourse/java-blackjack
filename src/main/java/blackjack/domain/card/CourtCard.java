package blackjack.domain.card;

import blackjack.domain.Pattern;
import java.util.List;

public class CourtCard extends Card {

    private static final List<String> COURT_SYMBOLS = List.of("J", "Q", "K");
    public static final int COURT_VALUE = 10;

    public CourtCard(Pattern pattern, String symbol) {
        super(pattern, symbol);
    }

    @Override
    protected void validateSymbol(String symbol) {
        if (!COURT_SYMBOLS.contains(symbol)) {
            throw new IllegalArgumentException(String.format("심볼은 J, Q, K 중 하나여야 합니다. 입력된 값 : %s", symbol));
        }
    }

    @Override
    public int getValue() {
        return COURT_VALUE;
    }

}
