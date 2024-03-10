package domain.blackjack;

import domain.card.CardDrawCondition;

public final class DealerCardDrawCondition implements CardDrawCondition {
    private final Gamer dealer;

    public DealerCardDrawCondition(Gamer dealer) {
        this.dealer = dealer;
    }

    @Override
    public boolean canDraw() {
        final int rawDealerDrawThresholdPoint = 16;
        SummationCardPoint dealerDrawThresholdPoint = new SummationCardPoint(rawDealerDrawThresholdPoint);

        SummationCardPoint summationCardPoint = dealer.getSummationCardPoint();
        return !summationCardPoint.isBiggerThan(dealerDrawThresholdPoint);
    }
}
