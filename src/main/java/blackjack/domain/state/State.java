package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.money.Money;

public interface State {
    State draw(final Card card);

    State stay();

    Cards cards();

    boolean isFinished();

    double profit(final Money money);
}
