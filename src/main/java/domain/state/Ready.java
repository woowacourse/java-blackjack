package domain.state;

import domain.card.Card;
import domain.card.Score;
import domain.state.exceptions.CardNotExistException;
import domain.state.exceptions.ProfitException;
import domain.state.exceptions.StayException;

import java.util.List;

public class Ready implements State {

    private static final int READY_SCORE = 0;

    @Override
    public Score score() {
        return new Score(READY_SCORE);
    }

    @Override
    public State draw(Card card) {
        return new Hit(card);
    }

    @Override
    public double profit(int base) {
        throw new ProfitException();
    }

    @Override
    public State stay() {
        throw new StayException();
    }

    @Override
    public List<Card> cards() {
        throw new CardNotExistException();
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
    public boolean isStay() {
        return false;
    }

    @Override
    public int handSize() {
        throw new CardNotExistException();
    }
}
