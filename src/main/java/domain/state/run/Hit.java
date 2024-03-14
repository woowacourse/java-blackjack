package domain.state.run;

import domain.card.Card;
import domain.state.State;
import domain.state.fininsh.Stand;

public class Hit extends Running {
    @Override
    public State draw(final Card card) {
        return null;
    }

    @Override
    public State stand() {
        return new Stand();
    }
}
