package blackjack.model.state;

import blackjack.model.card.Card;
import java.util.List;

public interface State {

    State add(final Card card);

    boolean isReady();

    boolean isHit();

    State stay();

    List<String> getCards();

    int getScore();
}
