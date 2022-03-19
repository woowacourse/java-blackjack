package blackjack.domain.state.finished;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldingCards;
import blackjack.domain.state.Started;
import blackjack.domain.state.State;

public abstract class Finished extends Started {

    public Finished(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public final State drawCard(Deck deck) {
        throw new IllegalStateException("[ERROR] 이미 종료된 상태입니다.");
    }

    @Override
    public final State stand() {
        throw new IllegalStateException();
    }
}
