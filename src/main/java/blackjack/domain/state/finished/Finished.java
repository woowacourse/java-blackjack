package blackjack.domain.state.finished;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;
import blackjack.domain.state.running.Running;

public abstract class Finished extends Running {

    public static final String FINISHED_DRAW_ERROR = "[Error] 턴이 종료된 상태에서는 드로우할 수 없습니다.";
    public static final String FINISHED_STAY_ERROR = "[Error] 턴이 종료된 상태에서는 스테이할 수 없습니다.";

    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public double earningRate() {
        return 1;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State draw(Card card) {
        throw new IllegalStateException(FINISHED_DRAW_ERROR);
    }

    @Override
    public State stay() {
        throw new IllegalStateException(FINISHED_STAY_ERROR);
    }

}
