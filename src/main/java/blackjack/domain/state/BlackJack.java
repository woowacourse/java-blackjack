package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public class BlackJack extends Terminant{

    public BlackJack(UserDeck userDeck) {
        super(userDeck);
    }
}
