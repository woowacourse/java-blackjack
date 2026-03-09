package domain;

import domain.constant.BlackJackRule;

public class Dealer extends Participant {

    public Dealer(String name, Hand hand) {
        super(name, hand);
    }

    static Dealer of(DrawStrategy drawStrategy) {
        return new Dealer("딜러", Hand.based(drawStrategy));
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= BlackJackRule.DEALER_PLAYABLE_THRESHOLD.value();
    }
}
