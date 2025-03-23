package blackjack.domain.participant;

import blackjack.domain.card.Deck;
import blackjack.domain.result.ProfitResult;

public interface GameRule {

    void dealInitialCards(Players players, Deck deck);

    ProfitResult calculateProfit(Players players);
}
