package blackjack.domain.state;

import blackjack.domain.card.Card;
import java.util.List;

public interface BlackjackGameState {

    void hit(final Card card);

    BlackjackGameState stand();

    boolean isFinished();

    List<Card> cards();

    int score();
}
