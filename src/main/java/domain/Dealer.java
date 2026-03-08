package domain;

import domain.constant.BlackJackRule;

public class Dealer extends Participant {

    public Dealer(String name, Hand hand) {
        super(name, hand);
    }

    static Dealer of(String name) {
        return new Dealer(name, Hand.empty());
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= BlackJackRule.DEALER_PLAYABLE_THRESHOLD.value();
    }
}
