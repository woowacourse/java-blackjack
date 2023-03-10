package domain.model;

import domain.vo.Batting;
import java.util.List;

public class Profit {

    private static final double NUMBER_TO_MULTIPLE_WHEN_DEFEAT = -1D;
    private static final double NUMBER_TO_MULTIPLE_WHEN_BLACKJACK_WIN = 1.5D;
    private static final double NUMBER_TO_MULTIPLE_WHEN_NORMAL_WIN = 1D;
    private static final double DRAW_VALUE = 0D;
    private static final int NUMBER_TO_MULTIPLE_DEALER_PROFIT = -1;
    private final double value;

    private Profit(final double value) {
        this.value = value;
    }


    public static Profit blackJackVictory(final Batting batting) {
        return new Profit(batting.getValue() * NUMBER_TO_MULTIPLE_WHEN_BLACKJACK_WIN);
    }

    public static Profit victory(final Batting batting) {
        return new Profit(batting.getValue() * NUMBER_TO_MULTIPLE_WHEN_NORMAL_WIN);
    }

    public static Profit defeat(final Batting batting) {
        return new Profit(batting.getValue() * NUMBER_TO_MULTIPLE_WHEN_DEFEAT);
    }

    public static Profit draw() {
        return new Profit(DRAW_VALUE);
    }

    public static Profit makeDealerProfitFrom(final List<Profit> playerProfits) {
        double sum = playerProfits.stream()
            .mapToDouble(Profit::getValue)
            .sum();
        return new Profit(sum * NUMBER_TO_MULTIPLE_DEALER_PROFIT);
    }

    public double getValue() {
        return value;
    }
}
