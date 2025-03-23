package blackjack.blackjack.participant;

import blackjack.blackjack.card.Deck;
import blackjack.blackjack.result.ProfitResult;

public interface GameRule {

    void dealInitialCards(Deck deck, int count);

    ProfitResult calculateProfit(Players players);
}
