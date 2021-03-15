package blackjack.domain.user;

public class BettingResult {

    private final String name;
    private final double earnedMoney;

    public BettingResult(String name, double earnedMoney) {
        this.name = name;
        this.earnedMoney = earnedMoney;
    }

    public String getName() {
        return name;
    }

    public Double getEarningMoney() {
        return earnedMoney;
    }
}
