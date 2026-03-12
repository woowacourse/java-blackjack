package domain;

public record RoundBetInfo(int round, User user, int betAmount) {

    public UserProfit toUserProfit(GameResult gameResult, int profit) {
        return new UserProfit(this, gameResult,profit);
    }
}
