package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public class Bust extends Terminant{

    public Bust(UserDeck userDeck) {
        super(userDeck);
    }
}
