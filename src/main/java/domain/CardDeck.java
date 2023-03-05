package domain;

import java.util.*;

public class CardDeck {
    private final Queue<Card> cards;

    public CardDeck(Queue<Card> cards) {
        this.cards = cards;
    }

    public Card poll(){
        return cards.poll();
    }
}
