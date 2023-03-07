package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.state.State;

import java.util.Objects;

public abstract class Participant {

    protected Name name;
    protected State currentState;

    public Participant(Name name, State currentState) {
        this.name = name;
        this.currentState = currentState;
    }

    abstract public void play(CardDeck cardDeck);

    public boolean isFinished() {
        return currentState.isFinished();
    }

    public boolean isBlackjack() {
        if(isFinished()){
            FinishedState state = FinishedState.checkFinishedState(currentState.getHand());
            return FinishedState.BLACKJACK.equals(state);
        }
        return false;
    }

    public boolean isBust() {
        if(isFinished()){
            FinishedState state = FinishedState.checkFinishedState(currentState.getHand());
            return FinishedState.BUST.equals(state);
        }
        return false;
    }

    public boolean isStand() {
        if(isFinished()){
            FinishedState state = FinishedState.checkFinishedState(currentState.getHand());
            return FinishedState.STAND.equals(state);
        }
        return false;
    }

    public int getScore() {
        return currentState.getOptimizedScore();
    }

    public Name getName() {
        return name;
    }

    public Hand getHand() {
        return currentState.getHand();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.getName());
    }
}
