package blackjack.domain.status;

import blackjack.domain.card.Card;

public class Blackjack implements Status{
    private Card[] cards;
    public Blackjack(Card... cards) {
        this.cards = cards;
    }
}
