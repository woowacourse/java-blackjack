package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public interface State {

    State draw(Card card);

    int calculateScore();

    CardHand getCardHand();
}
