package blackjack.domain.state;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldingCards;
import blackjack.domain.money.BetMoney;

public interface State {

    State drawCard(Deck deck);

    HoldingCards holdingCards();

    int cardSum();

    State stand();

    double profit(BetMoney betMoney, int dealerScore);
}
