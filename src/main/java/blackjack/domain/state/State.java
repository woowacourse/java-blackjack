package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;
import blackjack.domain.entry.vo.BettingMoney;

public interface State {

    State draw(Card card);

    State stay();

    State blackjack();

    boolean isFinished();

    double profit(BettingMoney bettingMoney);

    HoldCards getHoldCards();
}
