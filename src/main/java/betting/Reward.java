package betting;

import blackjackgame.Result;

public class Reward {
    private final int reward;

    private Reward(int reward) {
        this.reward = reward;
    }

    public static Reward from(Result result, BettingAmount bettingAmount) {
        return new Reward(bettingAmount.calculateRewardByResult(result));
    }

    public int getReward() {
        return reward;
    }
}
