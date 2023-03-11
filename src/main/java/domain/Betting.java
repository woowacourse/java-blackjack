package domain;

public class Betting {

    private final Integer bettingMoney;
    private final Integer profitMoney;


    public Betting(Integer bettingMoney, Integer profitMoney) {
        this.bettingMoney = bettingMoney;
        this.profitMoney = profitMoney;
    }

    public Betting giveBonus() {
        int newProfit = (int) (profitMoney + (bettingMoney * (1.5)));
        return new Betting(bettingMoney, newProfit);
    }

    public Betting calculateProfitByResult(Result result) {
        if (result == Result.LOSE) {
            return new Betting(bettingMoney, profitMoney + (bettingMoney*2));
        }
        if (result == Result.DRAW) {
            return new Betting(bettingMoney, bettingMoney);
        }
        return new Betting(bettingMoney, 0);
    }

    public Integer calculateFinalProfit() {
        return profitMoney - bettingMoney;
    }
}
