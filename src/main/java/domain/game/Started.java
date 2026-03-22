package domain.game;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public abstract class Started implements HandState {

    private final Hand hand;

    protected Started(Hand hand) {
        this.hand = hand;
    }

    protected Hand hand() {
        return hand;
    }

    @Override
    public List<Card> cards() {
        return hand.getCards();
    }

    @Override
    public int score() {
        return hand.calculateScore();
    }
}