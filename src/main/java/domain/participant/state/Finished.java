package domain.participant.state;

import domain.card.Card;
import domain.card.Hand;

import java.util.List;

public abstract class Finished implements State {
    private final Hand hand;

    public Finished(final Hand hand) {
        this.hand = hand;
    }


    @Override
    public State draw(final Card card) {
        return null;
    }

    @Override
    public State stand() {
        return null;
    }

    @Override
    public int score() {
        return hand.score().toInt();
    }

    @Override
    public double profitRate(final State state) {
        throw new UnsupportedOperationException("Stand, Blackjack, Bust 상태에서만 수익률을 계산할 수 있습니다.");
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
