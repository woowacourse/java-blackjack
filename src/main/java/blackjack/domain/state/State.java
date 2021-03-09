package blackjack.domain.state;

import blackjack.domain.card.Card;

public interface State {
    boolean isFinished();

    State draw(Card card);
}
