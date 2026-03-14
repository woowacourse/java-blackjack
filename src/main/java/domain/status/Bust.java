package domain.status;

import domain.participant.HandCards;

public final class Bust extends Finished {
    public Bust(HandCards cards) {
        super(cards);
    }

    @Override
    public double earningsRate(Status dealerStatus) {
        return -1.0;
    }
}
