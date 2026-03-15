package domain.state;

import domain.Score;
import domain.card.Card;
import java.math.BigDecimal;
import java.util.List;

public interface State {
    BigDecimal PROFIT_RATE_WIN = BigDecimal.ONE;
    BigDecimal PROFIT_RATE_TIE = BigDecimal.ZERO;
    BigDecimal PROFIT_RATE_LOSE = new BigDecimal("-1.0");
    BigDecimal PROFIT_RATE_BLACKJACK = new BigDecimal("1.5");

    State draw(Card card);
    State stay();
    boolean isFinished();
    boolean isBust();
    boolean isBlackjack();
    List<Card> cards();
    Score getScore();
    BigDecimal calculateProfitRate(State dealerState);
}
