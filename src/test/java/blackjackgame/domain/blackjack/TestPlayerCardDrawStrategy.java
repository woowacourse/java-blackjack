package blackjackgame.domain.blackjack;

public class TestPlayerCardDrawStrategy extends TestAbstractFirstCardDrawStrategy {
    private final CardHolder player;

    public TestPlayerCardDrawStrategy(CardHolder player) {
        this.player = player;
    }

    @Override
    boolean canDraw() {
        return !player.getSummationCardPoint().isDeadPoint();
    }
}
