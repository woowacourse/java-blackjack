package blackjack.model.state.finished;

import blackjack.model.MatchResult;
import blackjack.model.card.Card;
import blackjack.model.state.Hand;
import blackjack.model.state.State;
import java.util.List;

public abstract sealed class FinishedState
        implements State
        permits Blackjack, Bust, Stand {

    protected final Hand hand;

    public FinishedState(Hand hand) {
        this.hand = hand;
    }

    public abstract boolean isBlackjack();

    public abstract boolean isBust();

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public List<Card> getHandCards() {
        return hand.getCards();
    }

    @Override
    public int getTotal() {
        return hand.getTotal();
    }

    public MatchResult determineMatchResult(FinishedState other) {
        if (other.isBust()) {
            return MatchResult.LOSE;
        }
        if (this.isBust()) {
            return MatchResult.WIN;
        }
        if (this.isBlackjack() && other.isBlackjack()) {
            return MatchResult.DRAW;
        }
        if (other.isBlackjack()) {
            return MatchResult.BLACKJACK;
        }
        if (this.isBlackjack()) {
            return MatchResult.LOSE;
        }
        return compareTotal(other);
    }

    private MatchResult compareTotal(FinishedState other) {
        int myTotal = this.hand.getTotal();
        int otherTotal = other.hand.getTotal();
        if (myTotal < otherTotal) {
            return MatchResult.WIN;
        }
        if (otherTotal < myTotal) {
            return MatchResult.LOSE;
        }
        return MatchResult.DRAW;
    }
}
