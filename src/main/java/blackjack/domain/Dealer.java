package blackjack.domain;

import blackjack.domain.state.InitialState;
import blackjack.domain.state.State;

public class Dealer extends Participant {

    public Dealer(Name name, State state) {
        super(name, state);
    }

    public static Dealer createInitialStateDealer(Name name) {
        return new Dealer(name, new InitialState());
    }

    @Override
    public Dealer draw(Deck deck) {
        State newState = state().draw(deck);
        return new Dealer(name(), newState);
    }

    @Override
    public Dealer stand() {
        State newState = state().stand();
        return new Dealer(name(), newState);
    }
}
