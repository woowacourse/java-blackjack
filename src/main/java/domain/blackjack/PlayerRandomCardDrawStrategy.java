package domain.blackjack;

public class PlayerRandomCardDrawStrategy extends AbstractRandomCardDrawStrategy {
    private final Gamer player;

    public PlayerRandomCardDrawStrategy(Gamer player) {
        this.player = player;
    }

    @Override
    public boolean canDraw() {
        return !player.getSummationCardPoint().isDeadPoint();
    }
}
