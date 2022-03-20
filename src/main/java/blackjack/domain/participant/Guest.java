package blackjack.domain.participant;

import blackjack.domain.card.Deck;

public class Guest extends Player {

    public static final int LIMIT_POINT = 21;

    public Guest(String name) {
        this.deck = new Deck();
        this.name = name;
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
