package blackjackgame.domain.blackjack;

public class TestPlayerCardDrawStrategy extends TestAbstractFirstCardDrawStrategy {
    private final Gamer player;

    public TestPlayerCardDrawStrategy(Gamer player) {
        this.player = player;
    }

    @Override
    boolean canDraw() {
        return !player.getSummationCardPoint().isDeadPoint();
    }
}
