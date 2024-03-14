package blackjack.domain.state;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public interface State {

    State draw(Card card);

    State stay();

    Score calculateScore();

    boolean isFinished();

    CardHand getCardHand();
}
