package blackjack.blackjack.participant;

import blackjack.blackjack.card.Deck;
import blackjack.blackjack.result.ProfitResult;

public interface GameRule {

    void dealInitialCards(Players players, Deck deck);

    ProfitResult calculateProfit(Players players);
}
