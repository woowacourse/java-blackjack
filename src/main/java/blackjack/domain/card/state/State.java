package blackjack.domain.card.state;

import blackjack.domain.card.Card;

import java.util.List;

public interface State {
    State draw(Card card);

    State stand();

    List<Card> getCards();

    int calculate();

    boolean isBlackjack();

    boolean isBust();

    boolean isHit();

    boolean isStand();
}
