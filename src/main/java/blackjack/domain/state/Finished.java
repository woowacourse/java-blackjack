package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import java.util.Objects;

public abstract class Finished extends AbstractBlackjackGameState {

    private final Score score;

    Finished(final Cards cards, final Score score) {
        super(cards);
        Objects.requireNonNull(score, "score는 null이 들어올 수 없습니다.");
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
        return score.getScore();
    }

    @Override
    public final double profit(final int betMoney, final BlackjackGameState dealerGameState) {
        return betMoney * earningRate(dealerGameState);
    }

    abstract double earningRate(final BlackjackGameState blackjackGameState);
}
