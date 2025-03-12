package blackjack.model.player;

import blackjack.model.card.BlackJackCards;

public class Player extends BlackJackPlayer {

    private static final int DRAWABLE_POINT = BLACKJACK_POINT;

    public Player(final String name) {
        super(name);
    }

    @Override
    public BlackJackCards openInitialCards() {
        return blackJackCards;
    }

    @Override
    protected boolean canDrawMoreCard() {
        return getMinimumPoint() < DRAWABLE_POINT;
    }
}
