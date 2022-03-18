package blackjack.domain.state;

import blackjack.domain.card.Card;

public interface State {


    State stand();

    State hit(Card card);
}
