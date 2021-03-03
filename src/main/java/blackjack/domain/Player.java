package blackjack.domain;

import blackjack.domain.card.Card;

public class Player extends User {
    boolean isStay = false;

    public void stay() {
        this.isStay = true;
    }

    @Override
    public void hit(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isStay() {
        return isStay;
    }
}
