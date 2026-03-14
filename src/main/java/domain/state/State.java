package domain.state;

import domain.Score;
import domain.card.Card;
import java.math.BigDecimal;
import java.util.List;

public interface State {
    State draw(Card card);
    State stay();
    boolean isFinished();
    boolean isBust();
    boolean isBlackjack();
    List<Card> cards();
    Score getScore();
    BigDecimal calculateProfitRate(State dealerState);
}
