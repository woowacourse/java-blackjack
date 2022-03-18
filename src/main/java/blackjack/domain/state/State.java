package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;

public interface State {

    State draw(Card card);

    State stay();

    PlayerCards getCards();

    boolean isFinished();

    boolean isBust();

    boolean isBlackjack();
}
