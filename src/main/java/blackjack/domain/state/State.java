package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.user.Cards;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;

public interface State {
    State draw(Card card);

    State stay();

    boolean isFinish();

    Cards cards();

    double getProfit(Dealer dealer, Money money);

    double earningRate(Dealer dealer);
}
