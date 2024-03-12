package blackjackgame.domain.blackjack;

public class DealerRandomCardDrawStrategy extends AbstractRandomCardDrawStrategy {
    private final CardHolderGamer dealer;

    public DealerRandomCardDrawStrategy(CardHolderGamer dealer) {
        this.dealer = dealer;
    }

    @Override
    boolean canDraw() {
        SummationCardPoint summationCardPoint = dealer.getSummationCardPoint();
        return !summationCardPoint.isDealerAdditionalCardPoint();
    }
}
