package blackjackgame.domain.blackjack;

public class TestPlayerCardDrawStrategy extends TestAbstractFirstCardDrawStrategy {
    private final CardHolderGamer player;

    public TestPlayerCardDrawStrategy(CardHolderGamer player) {
        this.player = player;
    }

    @Override
    boolean canDraw() {
        return !player.getSummationCardPoint().isDeadPoint();
    }
}
