package blackjack.domain.strategy;

import blackjack.domain.Dealer;
import blackjack.domain.Player;
import blackjack.domain.PlayerResult;

public interface DealerStatusStrategy {
    PlayerResult calculateResultByPlayerStatus(Dealer dealer, Player player);
}
