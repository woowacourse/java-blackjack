package blackjack.view;

import blackjack.domain.card.CardScore;

public enum CardScoreName {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private final String name;

    CardScoreName(String name) {
        this.name = name;
    }

    public static String convert(CardScore cardScore) {
        return valueOf(cardScore.name()).name;
    }
}
