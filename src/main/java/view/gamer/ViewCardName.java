package view.gamer;

import domain.card.CardName;

public enum ViewCardName {
    ACE("A"),
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

    private final String output;

    static ViewCardName of(CardName cardName) {
        return valueOf(cardName.name());
    }

    ViewCardName(String output) {
        this.output = output;
    }

    String getOutput() {
        return output;
    }
}
