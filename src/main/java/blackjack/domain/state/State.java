package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.money.BettingMoney;
import blackjack.domain.money.Profits;

public interface State {
    boolean isFinished();

    State draw(Card card);

    Cards getCards();

    State stay();

    Profits profit(BettingMoney money);

    boolean isBlackJack();

    boolean isStay();

    boolean isBust();

    boolean isWin(State state);

    boolean isDraw(State state);
}
