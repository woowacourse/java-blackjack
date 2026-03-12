package domain.state.running;

import domain.card.vo.Card;
import domain.hitStrategy.HitStrategy;
import domain.participants.Hand;
import domain.state.State;
import domain.state.finished.Stay;
import java.util.List;

public abstract class Running implements State {

    protected final Hand hand;

    protected Running(Hand hand) {
        this.hand = hand;
    }

    public static Hit getStartState(Hand hand, HitStrategy hitStrategy) {
        return new Hit(hand, hitStrategy);
    }

//    public static Running getStartState(Hand hand, Participant participant, HitStrategy hitStrategy) {
//        if (hand.getScore().equals(BLACKJACK_SCORE)) {
//            return new BlackJack(hand, participant);
//        }
//        return new Hit(hand, participant, hitStrategy);
//    }

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
    public Integer getProfit(Integer betCost) {
        throw new IllegalStateException("Running은 수익금 반환을 못합니다!");
    }
}
