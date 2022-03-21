package blackjack.model.state;

import blackjack.model.betting.BettingRate;
import blackjack.model.card.Card;
import java.util.List;

public interface State {

    State add(final Card card);

    boolean isReady();

    boolean isHitAble();

    State stay();

    BettingRate calculateBettingRate(State otherState);

    List<Card> getCards();

    int getScore();

    boolean isBust();

    boolean isBlackjack();
}
