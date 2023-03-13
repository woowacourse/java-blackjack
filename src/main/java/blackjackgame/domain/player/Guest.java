package blackjackgame.domain.player;

import java.util.List;
import java.util.Objects;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.state.Ready;
import blackjackgame.domain.state.State;

public class Guest extends Player {
    private final Name name;

    private Guest(State state, final Name name) {
        super(state);
        this.name = name;
    }

    public static Guest of(List<Card> cards, Name name) {
        State state = new Ready();
        for (Card card : cards) {
            state = state.draw(card);
        }
        return new Guest(state, name);
    }

    @Override
    protected List<Card> startingCards() {
        return cards();
    }

    public boolean canHit() {
        return isRunning();
    }

    public String getName() {
        return name.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Guest guest = (Guest)o;
        return Objects.equals(name, guest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public void stay() {
        super.stay();
    }
}
