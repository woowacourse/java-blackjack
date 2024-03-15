package domain.state;

import domain.Card;

public interface State {
    State draw(Card card);

    State stand();
}
