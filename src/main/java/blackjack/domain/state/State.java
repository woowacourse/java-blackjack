package blackjack.domain.state;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public interface State {
    boolean isFinished();

    State draw(Card card);

    Cards getCards();

    State stay();

    BigDecimal profit(BettingMoney money);

    boolean isBlackJack();

    boolean isStay();

    boolean isBust();

    boolean isWin(State state);

    boolean isDraw(State state);
}
