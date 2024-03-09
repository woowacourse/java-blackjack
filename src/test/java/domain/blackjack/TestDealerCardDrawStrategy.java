package domain.blackjack;

public class TestDealerCardDrawStrategy extends TestAbstractFirstCardDrawStrategy {
    private final Gamer player;

    public TestDealerCardDrawStrategy(Gamer player) {
        this.player = player;
    }

    @Override
    boolean canDraw() {
        return !player.getSummationCardPoint().isDealerAdditionalCardPoint();
    }
}
