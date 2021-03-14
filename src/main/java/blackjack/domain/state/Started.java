package blackjack.domain.state;

import blackjack.domain.card.Hand;

public abstract class Started implements State {
    protected Hand hand;

    @Override
    public Hand hand() {
        return hand;
    }

    @Override
    public int softHandSum() {
        return hand.softSum();
    }
}
