package domain.state;

import domain.card.Card;

public interface State {

    State draw(final Card card);

    boolean isBust();

    boolean isBlackJack();
}
