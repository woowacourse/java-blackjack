package domain.state.running;

import domain.card.vo.Card;
import domain.participants.Hand;
import domain.state.State;
import domain.state.finished.BlackJack;
import domain.state.finished.Stay;
import java.util.List;

public abstract class Running implements State {

    protected final Hand hand;

    protected Running(Hand hand) {
        this.hand = hand;
    }

    public static State getStartState(Hand hand) {
        if (BlackJack.isBlackJak(hand)) {
            return new BlackJack(hand);
        }
        return new Hit(hand);
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
    public List<Card> getCards() {
        return hand.getCards();
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }

    @Override
    public Integer getProfit(State DealerState, Integer betCost) {
        throw new IllegalStateException("Running은 수익금 반환을 못합니다!");
    }
}
