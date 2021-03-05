package blackjack.domain.user;

import blackjack.domain.card.UserDeck;

public class Dealer extends User {

    private static final int DEALER_LIMIT_NUMBER = 17;

    public Dealer(UserDeck userDeck) {
        super(userDeck);
        super.DRAWABLE_NUMBER = DEALER_LIMIT_NUMBER;
    }
}
