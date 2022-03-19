package blackjack.model.state;

import blackjack.model.card.Card;
import java.util.List;

public interface State {

    State add(final Card card);

    boolean isReady();

    boolean isHit();

    List<String> getCards();

    State stay();
}
