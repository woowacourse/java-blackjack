package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;
import blackjack.domain.winning.WinningResult;

public interface State {

    State draw(Card card);

    State stay();

    double profit(double bettingMoney);

    Score calculateTotalScore();

    WinningResult decide(Cards dealerCards);

    boolean isFinished();

    Cards cards();
}
