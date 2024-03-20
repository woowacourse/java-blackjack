package domain.participant;

import domain.card.Card;
import domain.participant.state.State;

import java.util.List;
import java.util.Objects;

public abstract class Participant {
    private final Name name;
    protected State state;

    Participant(final Name name, final State state) {
        this.name = name;
        this.state = state;
    }

    public void drawCards(final List<Card> cards) {
        for (final Card card : cards) {
            state = state.draw(card);
        }
    }

    public void drawCard(final Card card) {
        state = state.draw(card);
    }

    public int score() {
        return state.score();
    }

    public abstract boolean canHit();

    public String name() {
        return name.getName();
    }

    public List<Card> hand() {
        return state.hand();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Participant player = (Participant) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
