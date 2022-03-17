package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public interface Participant {

    void hit(final Card card);

    void stay();

    boolean isFinished();

    int score();

    List<Card> cards();

    String getName();
}
