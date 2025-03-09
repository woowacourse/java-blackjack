package domain;

import java.util.List;

public class CardDeck {
    private final List<Card> cards;

    public CardDeck(List<Card> cards) {
        this.cards = cards;
    }

    public Card extractCard() {
        return cards.removeLast();
    }


}
