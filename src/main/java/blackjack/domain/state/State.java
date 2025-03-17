package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

public interface State {

    State draw(Card card);

    State stay();

    double profit(Cards dealerCards, double bettingMoney);

    Score calculateTotalScore();

    boolean isFinished();

    Cards cards();
}
