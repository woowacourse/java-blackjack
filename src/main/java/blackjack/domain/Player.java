package blackjack.domain;


public class Player extends Participant {
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    private final String name;
    private final int bettingAmount;

    public Player(String name) {
        this(name, 0);
    }

    public Player(String name, int bettingAmount) {
        this.name = name;
        this.bettingAmount = bettingAmount;
    }

    public String getName() {
        return name;
    }

    public double calculateProfit(ScoreCompareResult compareResult) {
        if (compareResult == ScoreCompareResult.PLAYER_WIN) {
            return calculateProfitWhenPlayerWins();
        }
        if (compareResult == ScoreCompareResult.PLAYER_LOSE) {
            return -bettingAmount;
        }
        return 0;
    }

    private double calculateProfitWhenPlayerWins() {
        if (isBlackjack()) {
            return (bettingAmount * BLACKJACK_PROFIT_RATE);
        }
        return bettingAmount;
    }
}
