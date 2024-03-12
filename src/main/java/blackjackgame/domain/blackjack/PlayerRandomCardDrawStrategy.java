package blackjackgame.domain.blackjack;

public class PlayerRandomCardDrawStrategy extends AbstractRandomCardDrawStrategy {
    private final CardHolder player;

    public PlayerRandomCardDrawStrategy(CardHolder player) {
        this.player = player;
    }

    @Override
    boolean canDraw() {
        return !player.isDead();
    }
}
