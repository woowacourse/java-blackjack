package blackjack.domain.card.state;

import blackjack.domain.card.Card;

public interface State {
    State draw(Card card);

    int calculate();

    boolean isBlackjack();

    boolean isBust();

    boolean isHit();

    boolean isStand();
}
