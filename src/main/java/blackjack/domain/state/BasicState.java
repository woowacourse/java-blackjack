package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public abstract class BasicState implements State {

    public static final double NORMAL_RATE = 1.0;
    public static final double ZERO_RATE = 0.0;

    private UserDeck userDeck;

    public BasicState(UserDeck userDeck) {
        this.userDeck = userDeck;
    }

    @Override
    public UserDeck getUserDeck() {
        return this.userDeck;
    }

    @Override
    public int getPoint() {
        return this.userDeck.score();
    }
}
