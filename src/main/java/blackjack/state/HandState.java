package blackjack.state;

import blackjack.domain.GameResult;
import blackjack.domain.Score;
import blackjack.domain.card.Card;
import java.util.List;

public interface HandState {
    HandState draw(Card card);

    HandState drawInitialCards(Card card1, Card card2);

    HandState stand();

    GameResult determineResult(HandState otherHandState);

    boolean isBlackjack();

    boolean isBust();

    boolean isFinished();
    Score getScore();

    List<Card> getCards();
}
