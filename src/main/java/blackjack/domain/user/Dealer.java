package blackjack.domain.user;

import blackjack.domain.card.Card;

public class Dealer extends User {
    private static final String DEALER_NAME = "딜러";
    private static final int HIT = 16;

    public Dealer() {
        super(DEALER_NAME);
    }

    public Card showOneCard() {
        return cards.oneCard();
    }

    @Override
    public boolean isHit() {
        return cards.calculateScore() <= HIT;
    }
}
