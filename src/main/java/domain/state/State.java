package domain.state;

import domain.card.Card;

public interface State {

    State drawCard(final Card card);

    boolean isBust();

    boolean isBlackJack();
}
