package model;

import java.util.List;

public class Cards {

    private final List<Card> cards;

    private Cards(final List<Card> cards) {
        this.cards = cards;
    }

    public static Cards from(final List<Card> cards) {
        return new Cards(cards);
    }
}
