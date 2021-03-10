package blackjack.domain.participant.state;

import blackjack.domain.Result;
import blackjack.domain.carddeck.Card;
import java.util.List;

public interface State {

    State draw(final Card card);

    State stay();

    List<Card> cards();

    boolean isFinished();

    Score score();

    Result calculatePlayerResult(final State state);
}
