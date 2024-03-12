package blackjackgame.domain.blackjack;

public class PlayerRandomCardDrawStrategy extends AbstractRandomCardDrawStrategy {
    private final CardHolderGamer player;

    public PlayerRandomCardDrawStrategy(CardHolderGamer player) {
        this.player = player;
    }

    @Override
    boolean canDraw() {
        return !player.isDead();
    }
}
