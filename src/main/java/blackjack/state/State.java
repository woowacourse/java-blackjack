package blackjack.state;

import blackjack.domain.card.Card;

public interface State {

    State hit(Card card);

    Cards cards();

    State stay();
}
