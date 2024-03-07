package domain;

public class PlayerRandomCardDrawStrategy extends AbstractRandomCardDrawStrategy {
    private final Gamer player;

    public PlayerRandomCardDrawStrategy(Gamer player) {
        this.player = player;
    }

    @Override
    protected boolean canDraw() {
        return !player.getSummationCardPoint().isBiggerThan(new SummationCardPoint(21));
    }
}
