package betting;

import game.GameResult;
import user.Participant;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BettingTable {
    private static final double BLACKJACK_REWARD_RATE = 1.5;

    private final Map<Participant, Long> bet = new HashMap<>();
    private Long dealerBet = 0L;

    public void betMoney(Participant participant, final long money) {
        bet.put(participant, money);
    }

    public Map<Participant, Long> calculateRewards(Map<Participant, GameResult> gameResult, Participant dealer) {
        Map<Participant, Long> rewards = new HashMap<>();

        for (Entry<Participant, GameResult> userGameResult : gameResult.entrySet()) {
            Participant participant = userGameResult.getKey();
            GameResult result = userGameResult.getValue();
            rewards.put(participant, calculateReward(participant, result));
        }

        rewards.put(dealer, dealerBet);
        return rewards;
    }

    private Long calculateReward(Participant participant, GameResult gameResult) {
        Long bettingMoney = bet.getOrDefault(participant, 0L);
        long reward = 0L;

        if (gameResult == GameResult.WIN) {
            reward = bettingMoney;
        }
        if (participant.isBlackjack() && gameResult == GameResult.WIN) {
            reward = (long) (bettingMoney * BLACKJACK_REWARD_RATE);
        }
        if (gameResult == GameResult.LOSE) {
            reward = -bettingMoney;
        }
        dealerBet -= reward;
        return reward;
    }
}
