package blackjack.model.player;

import blackjack.model.card.Cards;

public class Player extends BlackJackPlayer {

    private static final int DRAWABLE_POINT = BLACKJACK_POINT;

    public Player(final String name) {
        super(name);
    }

    @Override
    public Cards openInitialCards() {
        return cards;
    }

    @Override
    public boolean canDrawMoreCard() {
        return getMinimumPoint() < DRAWABLE_POINT;
    }
}
