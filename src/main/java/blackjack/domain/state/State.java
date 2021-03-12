package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public interface State {
    Hand hand();

    int softHandSum();

    State draw(Card card);
}
