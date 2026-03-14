package domain.status;

import domain.participant.HandCards;

public final class Stay extends Finished {
    public Stay(HandCards cards) {
        super(cards);
    }

    @Override
    public double earningsRate(Status dealerStatus) {
        if (dealerStatus instanceof Blackjack) {
            return -1.0;
        }
        if (dealerStatus instanceof Bust) {
            return 1.0;
        }
        if (cards.calculateScore() > dealerStatus.cards.calculateScore()) {
            return 1.0;
        }
        if (cards.calculateScore() == dealerStatus.cards.calculateScore()) {
            return 0.0;
        }
        return -1.0; // 승패 판정 필요함!
    }
}
