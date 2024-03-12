package blackjackgame.domain.blackjack;

import blackjackgame.domain.gamers.CardHolder;

public class TestDealerCardDrawStrategy extends TestAbstractFirstCardDrawStrategy {
    private final CardHolder player;

    public TestDealerCardDrawStrategy(CardHolder player) {
        this.player = player;
    }

    @Override
    boolean canDraw() {
        return !player.getSummationCardPoint().isDealerAdditionalCardPoint();
    }
}
