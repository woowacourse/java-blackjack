package blackjack.domain.user;

import blackjack.domain.card.UserDeck;
import blackjack.domain.state.StateFactory;

public class Dealer extends User {

    public static final int DEALER_CRITERIA = 16;

    public Dealer(UserDeck userDeck) {
        super(StateFactory.draw(userDeck, DEALER_CRITERIA));
    }
}
