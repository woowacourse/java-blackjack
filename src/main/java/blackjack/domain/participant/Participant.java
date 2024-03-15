package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.card.Score;
import blackjack.domain.participant.state.Blackjack;
import blackjack.domain.participant.state.Bust;
import blackjack.domain.participant.state.Ready;
import blackjack.domain.participant.state.State;

import java.util.Objects;

public abstract class Participant {
    private final Name name;
    private State state;

    public Participant(final String name) {
        this.name = new Name(name);
        this.state = new Ready(new CardHand());
    }

    public abstract boolean canReceiveCard();

    public void receiveCard(final Card card) {
        state = state.draw(card);
    }

    public Score calculateScore() {
        return state.calculateScore();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public void stay() {
        state = state.stay();
    }

    public boolean isBust() {
        return state instanceof Bust;
    }

    public boolean isBlackjack() {
        return state instanceof Blackjack;
    }

    public double calculateEarningRate(final ResultStatus resultStatus) {
        return state.calculateEarningRate(resultStatus);
    }

    public String getName() {
        return name.getValue();
    }

    public CardHand getCardHand() {
        return state.getCardHand();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Participant other = (Participant) o;
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
