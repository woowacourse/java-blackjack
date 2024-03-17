package domain.state;

import domain.Card;
import domain.Hand;

import java.util.List;

public class Finished implements State {
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
    public double profitRate() {
        return 0;
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public List<Card> hand() {
        return hand.cards();
    }
}
