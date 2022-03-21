package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public class Dealer extends Player {

    public static final String NAME = "딜러";
    public static final int LIMIT_POINT = 16;

    public Dealer() {
        this.deck = new Deck();
        this.name = NAME;
    }

    @Override
    int limit() {
        return LIMIT_POINT;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public boolean isGuest() {
        return false;
    }
}
