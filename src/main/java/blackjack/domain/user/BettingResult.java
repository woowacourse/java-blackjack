package blackjack.domain.user;

public class BettingResult {

    private final String name;
    private final double earnedMoney;

    public BettingResult(User user, MatchResult matchResult) {
        this.name = user.getName();
        this.earnedMoney = calculateEarningMoney(user, matchResult);
    }

    private double calculateEarningMoney(User user, MatchResult matchResult) {
        return user.getBettingMoney() * matchResult.getEarningRate();
    }

    public String getName() {
        return name;
    }

    public Double getEarningMoney() {
        return earnedMoney;
    }
}
