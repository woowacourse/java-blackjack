package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public class Stay extends Terminant{

    public Stay(UserDeck userDeck) {
        super(userDeck);
    }
}
