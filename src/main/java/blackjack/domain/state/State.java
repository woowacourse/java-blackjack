package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.player.BettingMoney;

public interface State {
    State draw(Card card);
    State stay();
    boolean isFinished();
    double calculateProfit(BettingMoney bettingMoney);
}
