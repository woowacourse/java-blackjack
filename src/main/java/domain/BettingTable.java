package domain;

import domain.user.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BettingTable {
    private final Map<User, Long> bet = new HashMap<>();
    private Long dealerBet = 0L;

    public void bettingMoney(User user, final long money) {
        bet.put(user, money);
    }

    public Map<User, Long> calculateRewards(Map<User, GameResult> gameResult, User dealer) {
        Map<User, Long> rewards = new HashMap<>();
        for (Entry<User, GameResult> userGameResult : gameResult.entrySet()) {
            User user = userGameResult.getKey();
            rewards.put(user, calculateReward(user, userGameResult.getValue(), user.isBlackjack()));
        }
        rewards.put(dealer, dealerBet);
        return rewards;
    }

    private Long calculateReward(User user, GameResult gameResult, boolean blackjack) {
        Long bettingMoney = bet.getOrDefault(user, 0L);
        if (blackjack && gameResult == GameResult.WIN) {
            dealerBet -= (long) (bettingMoney * 1.5);
            return (long) (bettingMoney * 1.5);
        }
        if (gameResult == GameResult.WIN) {
            dealerBet -= bettingMoney;
            return bettingMoney;
        }
        if (gameResult == GameResult.DRAW) {
            return 0L;
        }
        dealerBet += bettingMoney;
        return bettingMoney * -1;
    }
}
