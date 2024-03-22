package domain.state.run;

import static domain.Blackjack.PERFECT_SCORE;

import domain.card.Card;
import domain.card.Hands;
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

        if (isBusted()) {
            return new Bust(getHands());
        }

        return new Hit(getHands());
    }

    private boolean isBusted() {
        return getHands().calculateScore() > PERFECT_SCORE;
    }

    @Override
    public State stand() {
        return new Stand(getHands());
    }
}
