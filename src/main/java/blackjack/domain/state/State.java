package blackjack.domain.state;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public interface State {

    State hit(Card card);

    State stand();

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();

    Cards getCards();

    Score calculateScore();
}
