package domain.game;

import java.math.BigDecimal;

public enum Result {
    WIN("1"),
    LOSE("-1"),
    DRAW("0"),
    BLACKJACK("1.5");

    private final BigDecimal profitRate;

    Result(String profitRate) {
        this.profitRate = new BigDecimal(profitRate);
    }

    public static Result compare(int current, int opponent) {
        if (current > opponent) {
            return WIN;
        }
        if (current < opponent) {
            return LOSE;
        }
        return DRAW;
    }

    public static Result blackjackCompare(boolean isPlayerBlackjack, boolean isDealerBlackjack) {
        if (isPlayerBlackjack && isDealerBlackjack) {
            return DRAW;
        }
        if (isPlayerBlackjack) {
            return BLACKJACK;
        }
        return LOSE;
    }

    public static Result bustCompare(boolean isPlayerBust, boolean isDealerBust) {
        if (isPlayerBust) {
            return LOSE;
        }
        return WIN;
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }
}
