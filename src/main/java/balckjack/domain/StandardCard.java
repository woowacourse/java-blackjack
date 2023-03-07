package balckjack.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class StandardCard extends Card {

    public static List<StandardCard> createStandardCards(Pattern pattern) {
        return IntStream.range(2, 11)
            .mapToObj((symbol) -> new StandardCard(pattern, String.valueOf(symbol)))
            .collect(Collectors.toList());
    }

    public StandardCard(Pattern pattern, String symbol) {
        super(pattern, symbol);
    }

    @Override
    protected int getValue() {
        return Integer.parseInt(symbol);
    }
}
