package domain.state;

import domain.Card;
import domain.Hand;
import domain.Score;

import java.util.List;

public class Ready implements State {
    public static final int INITIAL_CARD_SIZE = 2;
    public static final Score BLACKJACK_SCORE = new Score(21);
    
    protected final Hand hand;

    public Ready(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(final Card card) {
        hand.add(card);
        if (hand.size() < INITIAL_CARD_SIZE) {
            return new Ready(hand);
        }
        if (hand.score().equals(BLACKJACK_SCORE)) {
            return new Blackjack(hand);
        }
        return new Hit(hand);
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("준비 상태에서는 스탠드 할 수 없습니다.");
    }

    @Override
    public int score() {
        return hand.score().toInt();
    }

    @Override
    public double profitRate(final State state) {
        throw new UnsupportedOperationException("준비 상태에서는 수익률을 계산할 수 없습니다.");
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public List<Card> hand() {
        return hand.cards();
    }
}
