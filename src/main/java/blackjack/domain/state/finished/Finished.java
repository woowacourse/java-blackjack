package blackjack.domain.state.finished;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldingCards;
import blackjack.domain.state.State;

public abstract class Finished implements State {

    private final HoldingCards holdingCards;

    protected Finished(HoldingCards holdingCards) {
        this.holdingCards = holdingCards;
    }

    @Override
    public final State drawCard(Deck deck) {
        throw new IllegalStateException("[ERROR] 이미 종료된 상태입니다.");
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
