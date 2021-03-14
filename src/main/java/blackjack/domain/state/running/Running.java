package blackjack.domain.state.running;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.state.State;

public abstract class Running extends State {

    public static final String RUNNING_EARNING_RATE_ERROR = "[Error] 턴 진행 중이여서 수익 비율을 계산할 수 없습니다.";

    public Running() {
    }

    public Running(Cards cards) {
        super(cards);
    }

    public abstract State draw(Card card);

    public abstract State stay();

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double earningRate() {
        throw new IllegalStateException(RUNNING_EARNING_RATE_ERROR);
    }

}
