package betting;

import game.GameResult;
import user.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BettingTable {
    private static final double BLACKJACK_REWARD_RATE = 1.5;

    private final Map<User, Long> bet = new HashMap<>();
    private Long dealerBet = 0L;

    public void betMoney(User user, final long money) {
        bet.put(user, money);
    }

    public Map<User, Long> calculateRewards(Map<User, GameResult> gameResult, User dealer) {
        Map<User, Long> rewards = new HashMap<>();

        for (Entry<User, GameResult> userGameResult : gameResult.entrySet()) {
            User user = userGameResult.getKey();
            GameResult result = userGameResult.getValue();
            rewards.put(user, calculateReward(user, result));
        }

        rewards.put(dealer, dealerBet);
        return rewards;
    }

    private Long calculateReward(User user, GameResult gameResult) {
        Long bettingMoney = bet.getOrDefault(user, 0L);
        long reward = 0L;

        if (gameResult == GameResult.WIN) {
            reward = bettingMoney;
        }
        if (user.isBlackjack() && gameResult == GameResult.WIN) {
            reward = (long) (bettingMoney * BLACKJACK_REWARD_RATE);
        }
        if (gameResult == GameResult.LOSE) {
            reward = -bettingMoney;
        }
        dealerBet -= reward;
        return reward;
    }
}
