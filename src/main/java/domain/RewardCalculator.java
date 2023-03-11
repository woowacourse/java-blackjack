package domain;


public class RewardCalculator {
    public static Money calculate(Result result, Money money) {
        double times = result.times();
        return money.multiply(times);
    }
}
