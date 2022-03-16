package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finish extends AbstractBlackjackGameState {

    private final int score;

    Finish(final Cards cards, final int score) {
        super(cards);
        this.score = score;
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

    @Override
    public final int score() {
        return score;
    }

    @Override
    public final double profit(final int betMoney, final BlackjackGameState blackjackGameState) {
        return betMoney * earningRate(blackjackGameState);
    }

    abstract double earningRate(final BlackjackGameState blackjackGameState);
}
