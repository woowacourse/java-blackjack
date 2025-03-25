package game;

import user.Dealer;
import user.Participant;

public enum GameResult {
    BLACKJACK(1.5),
    WIN(1),
    LOSE(-1),
    DRAW(0),
    ;

    private final double rewardRate;

    GameResult(double rewardRate) {
        this.rewardRate = rewardRate;
    }

    public double calculateReward(Participant participant, Dealer dealer) {
        double reward = participant.calculateReward(this.rewardRate);
        dealer.payReward(reward);
        return reward;
    }
}


