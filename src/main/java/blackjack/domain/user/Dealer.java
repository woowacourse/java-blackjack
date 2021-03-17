package blackjack.domain.user;

import blackjack.domain.card.UserDeck;
import blackjack.domain.money.Money;
import blackjack.domain.state.StateFactory;

public class Dealer extends User {

    public static final int DEALER_CRITERIA = 16;
    private static final int DEALER_INITIAL_MONEY = 0;

    public Dealer(UserDeck userDeck) {
        super(StateFactory.draw(userDeck, DEALER_CRITERIA), new Money(DEALER_INITIAL_MONEY));
    }
}
