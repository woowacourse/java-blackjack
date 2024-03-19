package blackjack.view;

import blackjack.domain.card.Rank;
import java.util.Arrays;

enum RankTranslator {

    ACE(Rank.ACE, "A"),
    TWO(Rank.TWO, "2"),
    THREE(Rank.THREE, "3"),
    FOUR(Rank.FOUR, "4"),
    FIVE(Rank.FIVE, "5"),
    SIX(Rank.SIX, "6"),
    SEVEN(Rank.SEVEN, "7"),
    EIGHT(Rank.EIGHT, "8"),
    NINE(Rank.NINE, "9"),
    TEN(Rank.TEN, "10"),
    JACK(Rank.JACK, "J"),
    QUEEN(Rank.QUEEN, "Q"),
    KING(Rank.KING, "K");

    private final Rank rank;
    private final String viewName;

    RankTranslator(final Rank rank, final String viewName) {
        this.rank = rank;
        this.viewName = viewName;
    }

    public static String translate(final Rank rank) {
        return Arrays.stream(RankTranslator.values())
                .filter(rankTranslator -> rankTranslator.rank == rank)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("매칭되는 슈트가 없습니다."))
                .viewName;
    }
}
