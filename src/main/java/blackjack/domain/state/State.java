package blackjack.domain.state;

import blackjack.domain.Score;
import blackjack.domain.card.Card;
import java.util.List;

public interface State {

    State hit(Card card);

    State stand();

    boolean isFinished();

    boolean isBlackjack();

    boolean isBust();

    List<Card> getCards();

    Score calculateScore();
}
