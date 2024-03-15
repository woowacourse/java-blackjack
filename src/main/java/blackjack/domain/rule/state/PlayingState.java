package blackjack.domain.rule.state;

import blackjack.domain.card.Hands;

public abstract class PlayingState implements State {

    private final Hands hands;

    protected PlayingState(final Hands hands) {
        this.hands = hands;
    }

    @Override
    public Hands hands() {
        return hands;
    }
}
