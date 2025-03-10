package blackjack.view;

import blackjack.domain.card.Rank;

public enum RankLabel {
    ACE("A"),
    ONE("1"),
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

    private final String displayName;

    RankLabel(String displayName) {
        this.displayName = displayName;
    }

    public static RankLabel fromRank(Rank rank) {
        return RankLabel.valueOf(rank.name());
    }

    public String getDisplayName() {
        return displayName;
    }
}

