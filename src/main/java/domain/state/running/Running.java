package domain.state.running;

import domain.card.Hand;
import domain.card.vo.Card;
import domain.state.Started;
import domain.state.State;
import domain.state.finished.Stay;
import java.util.List;

public abstract class Running extends Started {

    protected Running(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Integer getScore() {
        return hand.getScore();
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }

    @Override
    public Integer getProfit(State DealerState, Integer betCost) {
        throw new IllegalStateException("Running은 수익금 반환을 못합니다!");
    }

    @Override
    public List<Card> getCards() {
        return hand.getCards();
    }
}
