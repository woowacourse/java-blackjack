package domain.participant.state;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;

public class Ready implements State {
    protected final Hand hand;

    public Ready(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(final Card card) {
        hand.add(card);
        if (hand.isInitUnfinished()) {
            return new Ready(hand);
        }
        if (hand.isBlackjack()) {
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
    public boolean isHit() {
        return false;
    }

    @Override
    public List<Card> hand() {
        return hand.cards();
    }
}
