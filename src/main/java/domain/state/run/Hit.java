package domain.state.run;

import domain.card.Card;
import domain.state.Hands;
import domain.state.State;
import domain.state.fininsh.Bust;
import domain.state.fininsh.Stand;

public final class Hit extends Running {
    public Hit(final Hands hands) {
        super(hands);
    }

    @Override
    public State add(final Card card) {
        getHands().add(card);

        if (getHands().calculateScore() > 21) {
            return new Bust(getHands());
        }

        return new Hit(getHands());
    }

    @Override
    public State stand() {
        return new Stand(getHands());
    }
}
