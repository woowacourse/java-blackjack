package domain;

import domain.card.CanAddCardThreshold;

public class Dealer extends User {
    public Dealer() {
        super(CanAddCardThreshold.DEALER_THRESHOLD);
    }
}
