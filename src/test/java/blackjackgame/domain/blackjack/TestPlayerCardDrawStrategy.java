package blackjackgame.domain.blackjack;

import blackjackgame.domain.gamers.CardHolder;

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
