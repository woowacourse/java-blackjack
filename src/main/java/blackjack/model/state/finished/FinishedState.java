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
        if (this.isBust()) {
            return MatchResult.LOSE;
        }
        if (other.isBust()) {
            return MatchResult.WIN;
        }
        if (this.isBlackjack() && other.isBlackjack()) {
            return MatchResult.DRAW;
        }
        if (this.isBlackjack()) {
            return MatchResult.BLACKJACK;
        }
        if (other.isBlackjack()) {
            return MatchResult.LOSE;
        }
        return determineMatchResultByTotal(other);
    }

    private MatchResult determineMatchResultByTotal(FinishedState other) {
        int thisTotal = this.hand.getTotal();
        int otherTotal = other.hand.getTotal();
        if (thisTotal < otherTotal) {
            return MatchResult.LOSE;
        }
        if (otherTotal < thisTotal) {
            return MatchResult.WIN;
        }
        return MatchResult.DRAW;
    }
}
