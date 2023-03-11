package domain;


public class RewardCalculator {
    public static Money calculate(Result result, Money money) {
        double times = result.getTimes();
        return money.multiply(times);
    }

}
