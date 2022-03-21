package blackjack.model.state;

import blackjack.model.BettingRate;
import blackjack.model.card.Card;
import java.util.List;

public interface State {

    State add(final Card card);

    boolean isReady();

    boolean isHit();

    State stay();

    BettingRate calculateBettingRate(State otherState);

    List<String> getCards();

    int getScore();

    boolean isBust();

    boolean isBlackjack();
}
