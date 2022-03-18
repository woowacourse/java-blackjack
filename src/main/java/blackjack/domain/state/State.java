package blackjack.domain.state;

import blackjack.domain.card.Card;

public interface State {

    State hit(Card card);

    State stand();

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();
}
