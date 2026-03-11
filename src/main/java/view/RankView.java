package view;

import domain.card.Rank;
import java.util.Arrays;

public enum RankView {
    TWO(Rank.TWO, "2"),
    THREE(Rank.THREE, "3"),
    FOUR(Rank.FOUR, "4"),
    FIVE(Rank.FIVE, "5"),
    SIX(Rank.SIX, "6"),
    SEVEN(Rank.SEVEN, "7"),
    EIGHT(Rank.EIGHT, "8"),
    NINE(Rank.NINE, "9"),
    TEN(Rank.TEN, "10"),
    JACK(Rank.JACK, "10"),
    QUEEN(Rank.QUEEN, "10"),
    KING(Rank.KING, "10"),
    ACE(Rank.ACE, "11"),
    ;

    private final Rank rank;
    private final String displayName;

    RankView(Rank rank, String displayName) {
        this.rank = rank;
        this.displayName = displayName;
    }

    public static String from(Rank rank) {
        return Arrays.stream(values())
                .filter(view -> view.rank == rank)
                .findFirst()
                .map(view -> view.displayName)
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 수트입니다."));
    }
}
