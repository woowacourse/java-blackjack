package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public class Stay extends Terminant{

    public Stay(UserDeck userDeck) {
        super(userDeck);
    }

    @Override
    public double getProfitRate(State dealerState) {
        if ((dealerState instanceof BlackJack)
            || dealerState.getPoint() > super.getPoint()) {
            return -1 * NORMAL_RATE;
        }

        if (dealerState.getPoint() == super.getPoint()) {
            return ZERO_RATE;
        }

        return NORMAL_RATE;
    }
}
