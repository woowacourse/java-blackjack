package domain.participant;

import java.util.ArrayList;

public class Dealer extends Participant {
    private static final int DEALER_MINIMUM_VALUE= 17;
    public Dealer() {
        super(new ArrayList<>(), new Name(DEALER_NAME));
    }

    public boolean shouldHit() {
        return super.getHandValue() < DEALER_MINIMUM_VALUE;
    }
}
