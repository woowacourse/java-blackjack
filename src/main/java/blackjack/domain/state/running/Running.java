package blackjack.domain.state.running;

import blackjack.domain.card.Cards;
import blackjack.domain.state.AbstractBlackjackGameState;
import blackjack.domain.state.BlackjackGameState;

public abstract class Running extends AbstractBlackjackGameState {

    public Running(final Cards cards) {
        super(cards);
        checkBustCards(super.cards);
    }

    private void checkBustCards(final Cards cards) {
        if (cards.isBust()) {
            throw new IllegalArgumentException("Running상태는 버스트된 카드가 들어올 수 없습니다.");
        }
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final double profit(final int betMoney, final BlackjackGameState dealerGameState) {
        throw new IllegalStateException("Running상태는 수익을 계산할 수 없습니다.");
    }

    @Override
    public final int score() {
        throw new IllegalStateException("Running상태는 score를 계산할 수 없습니다.");
    }
}
