package blackjack.model;

public enum GameOutcome {
    BLACKJACK_WIN("승(블랙잭)", 1.5),
    WIN("승", 1),
    DRAW("무", 0),
    LOSE("패", -1),
    ;

    private final String format;
    private final double payoutRate;

    GameOutcome(String format, double payoutRate) {
        this.format = format;
        this.payoutRate = payoutRate;
    }

    public String getFormat() {
        return format;
    }

    public double getPayoutRate() {
        return payoutRate;
    }

    public static GameOutcome judge(GameSummary player, GameSummary dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return BLACKJACK_WIN;
        }
        if (dealer.isBlackjack()) {
            return LOSE;
        }

        if (player.isBust()) {
            return LOSE;
        }
        if (dealer.isBust()) {
            return WIN;
        }

        if (player.score() > dealer.score()) {
            return WIN;
        }
        if (player.score() < dealer.score()) {
            return LOSE;
        }

        return DRAW;
    }

    public GameOutcome reverse() {
        if (this == WIN || this == BLACKJACK_WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
