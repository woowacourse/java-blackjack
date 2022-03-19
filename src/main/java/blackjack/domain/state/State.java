package blackjack.domain.state;

import blackjack.domain.Outcome;
import blackjack.domain.bet.BetMoney;
import blackjack.domain.bet.Profit;
import blackjack.domain.card.Card;
import blackjack.domain.card.PlayerCards;

public interface State {

    State draw(Card card);

    State stay();

    PlayerCards getCards();

    boolean isFinished();

    boolean isBust();

    boolean isBlackjack();

    Profit profit(Outcome outcome, BetMoney money);
}
