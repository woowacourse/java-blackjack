package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.domain.player.Users;
import blackjack.domain.strategy.DealerStatusStrategy;

import java.util.*;

public class GameResult {
    private Map<User, UserResult> gameResult;

    private GameResult(Map<User, UserResult> result) {
        this.gameResult = result;
    }

    public static GameResult calculateGameResult(Dealer dealer, Users users) {
        Map<User, UserResult> result = new LinkedHashMap<>();
        for (User user : users.getUsers()) {
            result.put(user, calculatePlayerResult(dealer, user));
        }
        return new GameResult(result);
    }

    public static UserResult calculatePlayerResult(Dealer dealer, User user) {
        DealerStatusStrategy dealerStatusStrategy = dealer.getStatus().getDealerStatusStrategy();
        return dealerStatusStrategy.compute(dealer, user);
    }

    public Map<User, UserResult> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    public String getKoreanName(User user) {
        return gameResult.get(user).getKoreanName();
    }
}
