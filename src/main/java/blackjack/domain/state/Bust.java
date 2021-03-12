package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public class Bust extends Terminant {

    public Bust(UserDeck userDeck) {
        super(userDeck);
    }

    @Override
    public double getProfitRate(State dealerState) {
        return -1 * NORMAL_RATE;
    }
}
