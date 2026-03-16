package blackjack.domain;


import java.math.BigDecimal;

public class Player extends Participant {
    private static final BigDecimal BLACKJACK_PROFIT_RATE = BigDecimal.valueOf(1.5);

    private final String name;
    private final BigDecimal bettingAmount;

    public Player(String name) {
        this(name, BigDecimal.valueOf(0));
    }

    public Player(String name, BigDecimal bettingAmount) {
        this.name = name;
        this.bettingAmount = bettingAmount;
    }

    public String getName() {
        return name;
    }

    public BigDecimal calculateProfit(ScoreCompareResult compareResult) {
        if (compareResult == ScoreCompareResult.PLAYER_WIN) {
            return calculateProfitWhenPlayerWins();
        }
        if (compareResult == ScoreCompareResult.PLAYER_LOSE) {
            return bettingAmount.negate();
        }
        return BigDecimal.ZERO;
    }

    private BigDecimal calculateProfitWhenPlayerWins() {
        if (isBlackjack()) {
            return (bettingAmount.multiply(BLACKJACK_PROFIT_RATE));
        }
        return bettingAmount;
    }
}
