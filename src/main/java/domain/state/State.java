package domain.state;

import domain.BattleResult;
import domain.Bet;
import domain.Profit;
import domain.card.Card;
import domain.card.Cards;

public interface State {

    State hit(Card card);

    State stay();

    boolean isFinished();

    Cards cards();

    Profit Profit(Bet bet, BattleResult battleResult);
}
