package blackjackgame.domain.blackjack;

public class TestDealerCardDrawStrategy extends TestAbstractFirstCardDrawStrategy {
    private final CardHolderGamer player;

    public TestDealerCardDrawStrategy(CardHolderGamer player) {
        this.player = player;
    }

    @Override
    boolean canDraw() {
        return !player.getSummationCardPoint().isDealerAdditionalCardPoint();
    }
}
