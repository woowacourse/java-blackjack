package domain.game;

import domain.card.Card;
import domain.card.Hand;
import java.util.List;

public class Stay implements HandState {

    private final Hand hand;

    public Stay(Hand hand) {
        this.hand = hand;
    }

    @Override
    public Outcome versus(HandState other) {
        if (other instanceof Blackjack) {
            return Outcome.LOSE;
        }
        if (other instanceof Bust) {
            return Outcome.WIN;
        }
        return compareScore((Stay) other);
    }

    @Override
    public boolean canHit() {
        return false;
    }

    @Override
    public HandState draw(Card card) {
        return this;
    }

    @Override
    public HandState stay() {
        return this;
    }

    @Override
    public List<Card> cards() {
        return hand.getCards();
    }

    @Override
    public int score() {
        return hand.calculateScore();
    }

    private Outcome compareScore(Stay other) {
        if (this.score() > other.score()) {
            return Outcome.WIN;
        }
        if (this.score() == other.score()) {
            return Outcome.TIE;
        }
        return Outcome.LOSE;
    }
}
