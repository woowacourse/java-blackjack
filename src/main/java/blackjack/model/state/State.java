package blackjack.model.state;

import blackjack.model.card.Card;

public interface State {

    State add(final Card card);
}
