package domain;

public class DealerRandomCardDrawStrategy extends AbstractRandomCardDrawStrategy {
    private final Gamer dealer;

    public DealerRandomCardDrawStrategy(Gamer dealer) {
        this.dealer = dealer;
    }

    @Override
    protected boolean canDraw() {
        SummationCardPoint summationCardPoint = dealer.getSummationCardPoint();
        return !summationCardPoint.isBiggerThan(new SummationCardPoint(16));
    }
}
