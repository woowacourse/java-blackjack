package blackjack.domain.state;

import blackjack.domain.card.Card;
import java.util.List;

public interface State {

    State draw(Card card);

    State stay();

    List<Card> getCards();

    boolean isFinished();
}
