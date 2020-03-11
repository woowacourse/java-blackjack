package blackjack.domain;

import blackjack.domain.strategy.DealerStatusStrategy;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private Map<User, UserResult> gameResult = new HashMap<>();

    public UserResult calculatePlayerResult(Dealer dealer, User user) {
        DealerStatusStrategy dealerStatusStrategy = dealer.status.getDealerStatusStrategy();
        return dealerStatusStrategy.compute(dealer, user);
    }
}
