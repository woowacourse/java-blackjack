package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.user.Money;

public interface State {
    State draw(final Card card);

    boolean isFinish();

    double calculateProfit(Money money);
}
