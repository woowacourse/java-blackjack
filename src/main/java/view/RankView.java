package view;

import domain.card.Rank;
import java.util.Arrays;

public enum RankView {

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
    private final String value;

    RankView(Rank rank, String value) {
        this.rank = rank;
        this.value = value;
    }

    public static String from(final Rank rank) {
        final RankView result = Arrays.stream(RankView.values())
                .filter(rankView -> rankView.rank.equals(rank))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 순위입니다."));

        return result.value;
    }
}
