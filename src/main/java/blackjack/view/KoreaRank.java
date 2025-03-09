package blackjack.view;

import blackjack.domain.card.Rank;

public enum KoreaRank {
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

    KoreaRank(String displayName) {
        this.displayName = displayName;
    }

    public static KoreaRank fromRank(Rank rank) {
        return KoreaRank.valueOf(rank.name());
    }

    public String getDisplayName() {
        return displayName;
    }
}

