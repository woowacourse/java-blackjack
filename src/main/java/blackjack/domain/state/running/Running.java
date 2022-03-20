package blackjack.domain.state.running;

import blackjack.domain.cards.Cards;
import blackjack.domain.state.Init;
import blackjack.domain.state.finished.Finished;

public abstract class Running extends Init {
    public Running(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int profit(final int money, final Finished state) {
        throw new IllegalStateException("아직 수익을 계산할 수 없습니다.");
    }
}
