package domain;

import domain.constant.BlackJackRule;
import domain.constant.DealerName;

public class Dealer extends Participant {

    public Dealer(String name, Hand hand) {
        super(name, hand);
    }

    static Dealer of(DrawStrategy drawStrategy) {
        return new Dealer(DealerName.DEFAULT.dealerName(), Hand.based(drawStrategy));
    }

    @Override
    protected boolean isPlayable() {
        return hand.scoreSum() <= BlackJackRule.DEALER_PLAYABLE_THRESHOLD.value();
    }
}
