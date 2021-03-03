package blackjack.domain;

import blackjack.domain.card.Card;

public class Player extends User {
    @Override
    public void hit(Card card) {
        cards.add(card);
    }

    @Override
    public boolean isStay() {
        return false;
    }
}
