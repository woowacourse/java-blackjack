package blackjack.domain.state;

import blackjack.domain.BettingMoney;
import blackjack.domain.Card;
import blackjack.domain.Cards;

public interface State {

    public State draw(Card card);

    boolean isFinished();

    boolean isBust();

    boolean isBlackjack();

    public int profit(BettingMoney bettingMoney);

    Cards cards();

    public State stay();
}
