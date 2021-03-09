package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;
import blackjack.domain.user.Dealer;

public class DealerHit extends Consistent {

    public DealerHit(UserDeck userDeck) {
        super(userDeck);
    }

    @Override
    public State draw(Card card) {
        UserDeck userDeck = super.getUserDeck();
        userDeck.add(card);
        if (userDeck.isBust()) {
            return new Bust(userDeck);
        }

        if (userDeck.score() > Dealer.DEALER_CRITERIA) {
            return new Stay(userDeck);
        }
        return this;
    }
}
