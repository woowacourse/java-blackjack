package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.result.ProfitResult;
import blackjack.domain.result.ResultStatus;
import java.util.Map;

public interface GameRule {

    void dealInitialCards(Players players, Hand hand);

    ProfitResult calculateProfit(Map<Player, ResultStatus> playerScores);
}
