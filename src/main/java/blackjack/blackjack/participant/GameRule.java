package blackjack.blackjack.participant;

import blackjack.blackjack.result.ProfitResult;

public interface GameRule {

    ProfitResult calculateProfit(Players players);
}
