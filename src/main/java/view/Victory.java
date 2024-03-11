package view;

import model.card.CardDeck;

public enum Victory {
    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String value;

    Victory(String value) {
        this.value = value;
    }

    public static Victory of(int standard, int comparisonTarget) {
        if (standard == comparisonTarget || (standard > 21 && comparisonTarget > 21)) {
            return DRAW;
        }
        if (standard <= 21 && (standard > comparisonTarget || comparisonTarget > 21)) {
            return WIN;
        }
        return LOSE;
    }
    public String getValue() {
        return value;
    }
}
