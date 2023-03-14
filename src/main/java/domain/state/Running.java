package domain.state;

import domain.card.Card;
import domain.card.Hand;
import domain.card.Score;
import domain.state.exceptions.ProfitException;

import java.util.List;

public abstract class Running implements State {

    private final Hand hand;

    Running(Hand hand) {
        this.hand = hand;
    }

    @Override
    public Score score() {
        return hand.score();
    }

    @Override
    public State draw(final Card card) {
        hand.add(card);
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        if (hand.isBust()) {
            return new Bust(hand);
        }

        return new Hit(hand);
    }

    @Override
    public double profit(final int base) {
        throw new ProfitException();
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }

    @Override
    public List<Card> cards() {
        return hand.getCards();
    }

    @Override
    public boolean isHit() {
        return true;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isStay() {
        return false;
    }

    @Override
    public int handSize() {
        return hand.getCards().size();
    }
}
