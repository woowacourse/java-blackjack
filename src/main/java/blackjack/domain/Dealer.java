package blackjack.domain;

import blackjack.domain.Card.Card;
import blackjack.domain.Card.Cards;

public class Dealer {
    public static final int LOWER_BOUND = 16;
    private Cards cards;


    public Dealer() {
        cards = new Cards();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getSum() {
        return cards.getSum();
    }

    public boolean needMoreCard() {
        return getSum() <= LOWER_BOUND;
    }
}
