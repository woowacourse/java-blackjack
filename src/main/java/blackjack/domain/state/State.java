package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.math.BigDecimal;

public interface State {
    State draw(Card card);

    boolean isFinish();

    State stay();

    int calculateScore();

    Cards cards();

    BigDecimal profit(State dealerState, BigDecimal bigDecimal);
}
