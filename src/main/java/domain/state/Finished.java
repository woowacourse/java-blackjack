package domain.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;
import domain.state.exceptions.CanNotDrawCardException;
import domain.state.exceptions.StayException;

import java.util.List;

public abstract class Finished implements State {

    private final Hand hand;

    Finished(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public double profit(final int base) {
        return base * profitRate();
    }

    abstract double profitRate();

    @Override
    public Score score() {
        return hand.score();
    }

    @Override
    public State draw(final Card card) {
        throw new CanNotDrawCardException();
    }

    @Override
    public State stay() {
        throw new StayException();
    }

    @Override
    public List<Card> cards() {
        return hand.getCards();
    }

    @Override
    public boolean isHit() {
        return false;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public int handSize() {
        return hand.getCards().size();
    }
}
