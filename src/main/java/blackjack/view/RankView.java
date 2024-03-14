package blackjack.view;

import blackjack.domain.card.Rank;

import java.util.Arrays;

public enum RankView {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    KING("K"),
    QUEEN("Q"),
    JACK("J"),
    ACE("A");

    private final String rankName;

    RankView(String rankName) {
        this.rankName = rankName;
    }

    public static String convertRankName(Rank rank) {
        return Arrays.stream(RankView.values())
                .filter(rankView -> rankView.name().equals(rank.name()))
                .findFirst()
                .orElseThrow()
                .rankName;
    }
}
