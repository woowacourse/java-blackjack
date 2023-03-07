package blackjack.model.participant;

import blackjack.model.card.CardDeck;
import blackjack.model.card.CardScore;
import blackjack.model.state.State;

import java.util.Objects;

public abstract class Participant {

    protected static final int BLACKJACK_NUMBER = 21;

    protected Name name;
    protected State currentState;

    public Participant(Name name, State currentState) {
        this.name = name;
        this.currentState = currentState;
    }

    abstract public void play(CardDeck cardDeck);

    public boolean isBlackjack() {
        return currentState.isBlackjack();
    }

    public boolean isBust() {
        return currentState.isBust();
    }

    public boolean isStand() {
        return currentState.isStand();
    }

    public boolean isFinished() {
        return currentState.isFinished();
    }

    public CardScore getCardScore() {
        return currentState.getCardScore();
    }

    public int getScore() {
        if (isBust() || getCardScore().getBigScore() > BLACKJACK_NUMBER) {
            return getCardScore().getSmallScore();
        }
        return getCardScore().getBigScore();
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
