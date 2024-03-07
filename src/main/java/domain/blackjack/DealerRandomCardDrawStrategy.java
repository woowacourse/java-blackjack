package domain.blackjack;

public class DealerRandomCardDrawStrategy extends AbstractRandomCardDrawStrategy {
    private final Gamer dealer;

    public DealerRandomCardDrawStrategy(Gamer dealer) {
        this.dealer = dealer;
    }

    @Override
    boolean canDraw() {
        SummationCardPoint summationCardPoint = dealer.getSummationCardPoint();
        SummationCardPoint dealerDrawThresholdPoint = new SummationCardPoint(16);
        return !summationCardPoint.isBiggerThan(dealerDrawThresholdPoint);
    }
}
