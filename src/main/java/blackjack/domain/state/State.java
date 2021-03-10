package blackjack.domain.state;

import blackjack.domain.Card;
import blackjack.domain.Cards;
import blackjack.domain.Money;

public interface State {
    public State draw(Card card);

    boolean isFinished();

    public int profit(Money money);

    Cards cards();
}
