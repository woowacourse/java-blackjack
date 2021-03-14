package blackjack.domain.state;

import blackjack.domain.BettingMoney;
import blackjack.domain.Cards;

public interface State {

    public State draw(Cards cards);

    public boolean isFinished();

    boolean isBust(State state);

    boolean isBlackjack(State state);

    public int profit(BettingMoney bettingMoney);

    public State stay();


}
