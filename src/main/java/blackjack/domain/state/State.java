package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public interface State {
    State draw(Cards cards);

    State stay();

    boolean isFinished();

    Money profit(Money money, Dealer dealer);

    double profitRate(Dealer dealer);

    Cards getCards();
}
