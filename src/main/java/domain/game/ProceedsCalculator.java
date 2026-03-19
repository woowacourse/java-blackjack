package domain.game;

import domain.constant.Result;

public class ProceedsCalculator {

    private static final double BLACKJACK_PROCEEDS_RATE = 1.5;
    private static final double WIN_PROCEEDS_RATE = 1.0;
    private static final double DRAW_PROCEEDS_RATE = 0.0;
    private static final double LOSE_PROCEEDS_RATE = -1.0;

    private ProceedsCalculator() {
    }

    public static double calculate(Long bettingMoney, Result result) {
        if (result == Result.BLACKJACK) {
            return bettingMoney * BLACKJACK_PROCEEDS_RATE;
        }
        if (result == Result.WIN) {
            return bettingMoney * WIN_PROCEEDS_RATE;
        }
        if (result == Result.DRAW) {
            return bettingMoney * DRAW_PROCEEDS_RATE;
        }
        return bettingMoney * LOSE_PROCEEDS_RATE;
    }
}