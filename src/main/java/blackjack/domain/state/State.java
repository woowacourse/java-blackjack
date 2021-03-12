package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public interface State {
    Hand hand();

    State draw(Card card);
}
