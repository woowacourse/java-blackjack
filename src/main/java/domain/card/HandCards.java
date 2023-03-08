package domain.card;

import java.util.ArrayList;
import java.util.List;

public class HandCards {
    private final List<Card> cards;
    private int aceCount;

    public HandCards() {
        this.cards = new ArrayList<>();
        this.aceCount = 0;
    }

    public void takeCard(Card card) {
        cards.add(card);
        if (card.getName().contains("A")) {
            aceCount++;
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getAceCount() {
        return aceCount;
    }
}
