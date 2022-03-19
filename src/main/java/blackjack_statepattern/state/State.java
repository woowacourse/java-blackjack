package blackjack_statepattern.state;

import blackjack_statepattern.Card;

public interface State {
    State draw(Card card);
}
