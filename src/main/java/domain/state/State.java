package domain.state;

import domain.Card;

public interface State {
    State hit(Card card);

    State stand();
}
