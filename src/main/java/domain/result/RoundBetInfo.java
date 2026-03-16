package domain.result;

import domain.player.User;

public record RoundBetInfo(int round, User user, double betAmount) {

    public UserProfit toUserProfit(GameResult gameResult, double profit) {
        return new UserProfit(this, gameResult,profit);
    }
}
