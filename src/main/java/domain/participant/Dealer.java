package domain.participant;

import domain.CanAddCardThreshold;

public final class Dealer extends Participant {
    public Dealer() {
        super(CanAddCardThreshold.DEALER_THRESHOLD);
    }
}
