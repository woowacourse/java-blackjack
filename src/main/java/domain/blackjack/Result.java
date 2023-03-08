package domain.blackjack;

import domain.player.BettingMoney;

public enum Result {
    WIN("승", 2.0),
    DRAW("무", 1.0),
    LOSE("패", 0.0),
    ;

    private static final double BLACKJACK_BONUS_RATIO = 0.5;

    private final String value;
    private final double ratio;

    Result(String value, double ratio) {
        this.value = value;
        this.ratio = ratio;
    }

    public Result convertToOpposite() {
        if (this == WIN) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return DRAW;
    }

    public BettingMoney payOut(BettingMoney betAmount, boolean isBlackjack) {
        if (isBlackjack && this == WIN) {
            return betAmount.applyRatio(this.ratio + BLACKJACK_BONUS_RATIO);
        }

        return betAmount.applyRatio(this.ratio);
    }

    public String getValue() {
        return value;
    }

    public double getRatio() {
        return ratio;
    }
}
