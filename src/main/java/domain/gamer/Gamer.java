package domain.gamer;

import domain.card.CardDeck;
import domain.card.Hand;
import domain.state.State;
import domain.state.started.finished.Blackjack;
import domain.state.started.running.Hit;

public abstract class Gamer {
    protected State state;

    public Gamer(Hand hand) {
        if (hand.isBlackjack()) {
            state = new Blackjack(hand);
            return;
        }
        state = new Hit(hand);
    }

    public int getScore() {
        return state.getHand().calculateTotalPoint();
    }

    public Hand getHand() {
        return state.getHand();
    }

    public abstract String getNickname();

    public void stay() {
        state = state.stay();
    }

    public void hit(CardDeck deck) {
        if (!state.isFinished()) {
            state = state.hit(deck.drawCard());
        }
    }

    public boolean isFinished() {
        return state.isFinished();
    }
}
