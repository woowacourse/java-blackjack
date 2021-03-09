package blackjack.domain.user;

import blackjack.domain.card.UserDeck;

public class Dealer extends User {

    public static final int DEALER_CRITERIA = 16;

    public Dealer(UserDeck userDeck) {
        super(userDeck);
    }

    @Override
    public boolean isAvailableDraw() {
        return super.checkDrawRule(DEALER_CRITERIA);
    }
}
