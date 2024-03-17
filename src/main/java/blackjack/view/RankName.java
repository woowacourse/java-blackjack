package blackjack.view;

import blackjack.domain.card.Rank;

public enum RankName {
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

    private final String name;

    RankName(String name) {
        this.name = name;
    }

    public static String convert(Rank rank) {
        return valueOf(rank.name()).name;
    }
}
