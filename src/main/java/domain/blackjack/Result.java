package domain.blackjack;

import domain.player.BettingMoney;

public enum Result {
    BLACKJACK("승(블랙잭)", 2.5),
    WIN("승", 2.0),
    DRAW("무", 1.0),
    LOSE("패", 0.0),
    ;

    private final String value;
    private final double ratio;

    Result(String value, double ratio) {
        this.value = value;
        this.ratio = ratio;
    }

    public Result convertToOpposite() {
        if (this == WIN || this == BLACKJACK) {
            return LOSE;
        }

        if (this == LOSE) {
            return WIN;
        }

        return DRAW;
    }

    public BettingMoney payOut(BettingMoney bettingMoney) {
        return bettingMoney.applyRatio(this.ratio);
    }

    public String getValue() {
        return value;
    }

    public double getRatio() {
        return ratio;
    }
}
