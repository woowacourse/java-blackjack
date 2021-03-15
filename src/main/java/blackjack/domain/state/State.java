package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public interface State {

    boolean isFinished();

    State draw(UserDeck userDeck);
}
