package blackjackgame.domain.blackjack;

public class PlayerRandomCardDrawStrategy extends AbstractRandomCardDrawStrategy {
    private final Gamer player;

    public PlayerRandomCardDrawStrategy(Gamer player) {
        this.player = player;
    }

    @Override
    boolean canDraw() {
        SummationCardPoint summationCardPoint = player.getSummationCardPoint();
        return !summationCardPoint.isDeadPoint();
    }
}
