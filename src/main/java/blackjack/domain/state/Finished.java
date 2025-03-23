package blackjack.domain.state;

import blackjack.domain.card.Hand;
import java.math.BigDecimal;

public abstract class Finished extends Started implements State {

    public Finished(final Hand cards, final StateType stateType) {
        super(cards, stateType);
    }

    @Override
    public State receiveCards(Hand hand) {
        throw new IllegalStateException("[ERROR] 종료된 게임에서는 카드를 받을 수 없습니다.");
    }

    @Override
    public State stay() {
        throw new IllegalStateException("[ERROR] 종료된 게임에서는 stay할 수 없습니다.");
    }

    @Override
    public boolean isNotFinished() {
        return false;
    }

    @Override
    public abstract BigDecimal profit(BigDecimal bettingAmount, State dealerState);
}
