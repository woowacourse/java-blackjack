package blackjack.view;

import blackjack.domain.card.Rank;

public enum RankDescription {
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
    KING("K"),
    ACE("A");

    private final String description;

    RankDescription(String description) {
        this.description = description;
    }

    public static RankDescription from(Rank rank) {
        return RankDescription.valueOf(rank.name());

    }

    public String getDescription() {
        return description;
    }
}
