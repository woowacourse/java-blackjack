package blackjack.view.display;

import blackjack.card.Rank;
import java.util.Arrays;

public enum CardRankDisplay {

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
    private final String display;

    CardRankDisplay(Rank rank, String display) {
        this.rank = rank;
        this.display = display;
    }

    public static CardRankDisplay getDisplayByRank(Rank rank) {
        return Arrays.stream(CardRankDisplay.values())
                .filter(displayNumber -> displayNumber.rank == rank)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 수입니다."));
    }

    public String getDisplay() {
        return display;
    }
}
