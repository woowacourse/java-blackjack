package blackjack.domain.user;

import blackjack.domain.card.UserDeck;

public class Dealer extends User {

    private static final int DEALER_DRAW_NUMBER = 17;

    public Dealer(UserDeck userDeck) {
        super(userDeck);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished() || score() >= DEALER_DRAW_NUMBER;
    }
}
