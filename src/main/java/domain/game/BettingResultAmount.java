package domain.game;

import domain.participants.BettingAmount;
import java.util.Objects;

public class BettingResultAmount {
    private static final int NEUTRAL_RESULT = 0;

    private final int money;

    public BettingResultAmount(int money) {
        this.money = money;
    }

    public BettingResultAmount(BettingAmount bettingAmount, GameResult gameResult) {
        this(calculateBettingResult(bettingAmount, gameResult));
    }

    public int getMoney() {
        return money;
    }

    private static int calculateBettingResult(BettingAmount bettingAmount, GameResult gameResult) {
        if (gameResult == GameResult.WIN) {
            return bettingAmount.getMoney();
        }
        if (gameResult == GameResult.LOSE) {
            return -bettingAmount.getMoney();
        }
        return NEUTRAL_RESULT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingResultAmount that = (BettingResultAmount) o;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(money);
    }
}
