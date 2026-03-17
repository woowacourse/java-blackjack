package domain.player;

import static util.Constants.DEALER_REFERENCE_POINT;

import domain.player.attribute.Hand;
import domain.player.attribute.Name;

public class Dealer extends Participant {

    public Dealer(Name name, Hand hand) {
        super(name, hand);
    }

    public boolean isTotalScore16OrLess() {
        return getTotalScore() <= DEALER_REFERENCE_POINT;
    }

    public String getFirstHand() {
        return hand.getFirstCardInfo();
    }
}
