package blackjackgame.domain.blackjack;

public class DealerRandomCardDrawStrategy extends AbstractRandomCardDrawStrategy {
    private final CardHolder dealer;

    public DealerRandomCardDrawStrategy(CardHolder dealer) {
        this.dealer = dealer;
    }

    @Override
    boolean canDraw() {
        SummationCardPoint summationCardPoint = dealer.getSummationCardPoint();
        return !summationCardPoint.isDealerAdditionalCardPoint();
    }
}
