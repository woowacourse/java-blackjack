package domain.state.run;

import domain.card.Card;
import domain.player.Hands;
import domain.state.State;

public class Hit extends Running {
    public Hit(final Hands hands) {
        super(hands);
    }

    @Override
    public State hit(final Card card) {

        return null;
    }

    @Override
    public State stand() {
        return null;
    }
}
