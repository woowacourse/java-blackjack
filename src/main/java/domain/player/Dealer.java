package domain.player;

import static util.Constants.DEALER_REFERENCE_POINT;

public class Dealer extends Participant {

    public Dealer(String name) {
        super(name);
    }

    public boolean isTotalScore16OrLess() {
        return getTotalScore() <= DEALER_REFERENCE_POINT;
    }

    public String getFirstHand() {
        return hand.getFirstCardInfo();
    }
}
