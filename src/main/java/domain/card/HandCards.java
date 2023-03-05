package domain.card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public void takeCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
