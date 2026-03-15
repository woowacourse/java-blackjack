package domain.state;

import domain.Score;
import domain.card.Card;
import domain.participant.Hand;
import java.util.List;

public abstract class Started implements State {
    private final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    protected Hand getHand() {
        return hand;
    }

    @Override
    public List<Card> cards() {
        return List.copyOf(hand.getCards());
    }

    @Override
    public Score getScore() {
        return hand.calculateScore();
    }

    @Override
    public boolean isBust() {
        return hand.isBust();
    }

    @Override
    public boolean isBlackjack() {
        return hand.isBlackjack();
    }
}
