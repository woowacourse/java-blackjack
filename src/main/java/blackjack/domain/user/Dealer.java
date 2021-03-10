package blackjack.domain.user;

import blackjack.domain.card.UserDeck;

public class Dealer extends User {

    private static final int DEALER_DRAW_NUMBER = 17;

    public Dealer(UserDeck userDeck) {
        super(userDeck);
    }

    @Override
    public boolean isAvailableDraw() {
        return !this.isBlackjack() && !this.isBust() && this.getScore() < DEALER_DRAW_NUMBER;
    }
}
