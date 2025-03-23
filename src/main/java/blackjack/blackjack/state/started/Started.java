package blackjack.blackjack.state.started;

import blackjack.blackjack.card.Hand;
import blackjack.blackjack.state.State;
import blackjack.blackjack.state.StateType;

public abstract class Started implements State {

    protected final Hand cards;
    protected final StateType stateType;

    public Started(final Hand cards, final StateType stateType) {
        this.cards = cards;
        this.stateType = stateType;
    }

    @Override
    public int calculateScore() {
        return cards.calculateScore();
    }

    @Override
    public Hand cards() {
        return cards;
    }

    @Override
    public StateType getStateType() {
        return stateType;
    }
}
