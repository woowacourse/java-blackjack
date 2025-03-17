package blackjack.domain.participant;

import blackjack.domain.card.Hand;
import blackjack.domain.participant.gamer.Player;
import blackjack.domain.result.ProfitResult;
import java.util.Map;

public interface GameRule {

    void dealInitialCards(Players players, Hand hand);

    ProfitResult calculateProfit(Map<Player, Hand> playerScores);
}
