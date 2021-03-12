package blackjack.domain.state;

import blackjack.domain.card.Card;

public interface State {
    State draw(final Card card);

    boolean isFinish();

    double profit(double money);
}
