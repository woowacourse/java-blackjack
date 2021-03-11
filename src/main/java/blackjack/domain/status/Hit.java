package blackjack.domain.status;

import blackjack.domain.card.Card;

public class Hit implements Status{
    private Card[] cards;

    public Hit(Card[] cards) {
        this.cards = cards;
    }
}
