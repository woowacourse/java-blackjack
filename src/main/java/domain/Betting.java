package domain;

public class Betting {

    private static final int MINIMUM_MONEY = 1;
    private final Integer bettingMoney;
    private final Integer profitMoney;


    public Betting(Integer bettingMoney, Integer profitMoney) {
        validateBettingInput(bettingMoney);
        this.bettingMoney = bettingMoney;
        this.profitMoney = profitMoney;
    }

    private static void validateBettingInput(Integer bettingMoney) {
        if (bettingMoney < MINIMUM_MONEY) {
            throw new IllegalArgumentException("베팅금액은 0원 초과 금액이여야 합니다. 입력한 숫자 : " + bettingMoney);
        }
    }

    public Betting giveBonus() {
        int newProfit = (int) (profitMoney + (bettingMoney * (1.5)));
        return new Betting(bettingMoney, newProfit);
    }

    public Betting calculateProfitByResult(Result result) {
        if (result == Result.LOSE) {
            return new Betting(bettingMoney, profitMoney + (bettingMoney * 2));
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
