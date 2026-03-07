package domain.player;

import domain.card.Card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }
}
