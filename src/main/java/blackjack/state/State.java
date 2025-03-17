package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import java.util.List;

public interface State {
    State draw(Card card);

    State drawInitialCards(Card card1, Card card2);

    State stand();

    GameResult determineResult(State otherState);

    boolean isBlackjack();

    boolean isBust();

    boolean isFinished();
    Score getScore();

    List<Card> getCards();
}
