package blackjack.domain;

import blackjack.domain.strategy.DealerStatusStrategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private Map<User, UserResult> gameResult = new HashMap<>();

    public void calculateGameResult(Dealer dealer, List<User> users) {
        for (User user : users) {
            gameResult.put(user, calculatePlayerResult(dealer, user));
        }
    }

    public UserResult calculatePlayerResult(Dealer dealer, User user) {
        DealerStatusStrategy dealerStatusStrategy = dealer.status.getDealerStatusStrategy();
        return dealerStatusStrategy.compute(dealer, user);
    }

    public Map<User, UserResult> getGameResult() {
        return gameResult;
    }

    public String getKoreanName(User user) {
        return gameResult.get(user).getKoreanName();
    }
}
