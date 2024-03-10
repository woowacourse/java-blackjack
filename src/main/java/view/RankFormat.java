package view;

import domain.card.Rank;

import java.util.Arrays;

public enum RankFormat {

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
    private final String name;

    RankFormat(Rank rank, String name) {
        this.rank = rank;
        this.name = name;
    }

    public static RankFormat from(Rank rank) {
        return Arrays.stream(values())
                .filter(rankFormat -> rankFormat.rank == rank)
                .findFirst()
                .orElseThrow();
    }

    public String getName() {
        return name;
    }
}
