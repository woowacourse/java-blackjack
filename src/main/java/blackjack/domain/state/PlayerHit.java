package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.UserDeck;

public class PlayerHit extends Consistent {

    public PlayerHit(UserDeck userDeck) {
        super(userDeck);
    }

    @Override
    public State draw(Card card) {
        UserDeck userDeck = super.getUserDeck();
        userDeck.add(card);
        if (userDeck.isBust()) {
            return new Bust(userDeck);
        }
        return this;
    }
}
