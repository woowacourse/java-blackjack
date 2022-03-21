package blackjack.domain.game;

import blackjack.domain.card.Card;
import blackjack.domain.state.Blackjack;
import blackjack.domain.state.Bust;
import blackjack.domain.state.Ready;
import blackjack.domain.state.State;

import java.util.List;

public abstract class Gamer {

    static final String DEALER_NAME = "딜러";

    private final String name;
    State state = new Ready();

    Gamer(final String name) {
        this.name = name;
    }

    public PlayingCards openCards() {
        return state.getPlayingCards();
    }

    public int sumOfCards() {
        return state.cardTotal();
    }

    public void deal(final List<Card> cards) {
        for (Card card : cards) {
            state = state.draw(card);
        }
    }

    public void draw(final Card card) {
        state = state.draw(card);
    }

    public void stay() {
        state = state.stay();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public boolean isBust() {
        return state.isBust();
    }

    public String getName() {
        return name;
    }

    abstract boolean isDrawable();
}
