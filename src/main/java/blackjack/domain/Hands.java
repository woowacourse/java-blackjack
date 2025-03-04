package blackjack.domain;

import blackjack.domain.card.Card;
import java.util.ArrayList;
import java.util.List;

public class Hands {

    private final List<Card> cards;

    public Hands() {
        this.cards = new ArrayList<>();
    }

    public void addNewCard(Card card) {
        cards.add(card);
    }

    public int calculateSum(){
        int total = 0;
        for (Card card : cards) {
            total += card.getValue();
        }
        return total;
    }

    public boolean isSumBelow(final int criteria) {
        return calculateSum() <= criteria;
    }
}
