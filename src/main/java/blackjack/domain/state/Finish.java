package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finish extends AbstractBlackjackGameState {

    Finish(final Cards cards) {
        super(cards);
    }

    @Override
    public final BlackjackGameState hit(final Card card) {
        throw new IllegalStateException("Finish상태는 hit할 수 없습니다.");
    }

    @Override
    public final BlackjackGameState stay() {
        throw new IllegalStateException("Finish상태는 stay할 수 없습니다.");
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
