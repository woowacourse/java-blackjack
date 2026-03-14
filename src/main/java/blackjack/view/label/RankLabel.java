package blackjack.view.label;

import blackjack.model.card.Rank;
import java.util.Arrays;

public enum RankLabel {
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
    KING(Rank.KING, "K"),
    QUEEN(Rank.QUEEN, "Q"),
    JACK(Rank.JACK, "J");

    private final Rank rank;
    private final String label;

    RankLabel(Rank rank, String label) {
        this.rank = rank;
        this.label = label;
    }

    public static String from(Rank rank) {
        return Arrays.stream(values())
                .filter(label -> label.rank == rank)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("부적절한 랭크입니다."))
                .label;
    }
}
