package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;

public interface State {

    State draw(final Card card);

    State stay();

    boolean isFinished();
}
