package blackjack.view;

import blackjack.domain.card.Rank;
import java.util.Arrays;

public enum RankView {

    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String symbol;

    RankView(final String symbol) {
        this.symbol = symbol;
    }

    public static String toSymbol(final Rank rank) {
        return Arrays.stream(RankView.values())
                .filter(rankView -> rankView.name().equals(rank.name()))
                .findFirst()
                .orElseThrow()
                .symbol;
    }
}
