package blackjack.model;

public enum GameResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String format;

    GameResult(String format) {
        this.format = format;
    }

    public String getFormat() {
        return format;
    }

    public static GameResult judge(GameSummary player, GameSummary dealer) {
        if (player.isBlackjack() && dealer.isBlackjack()) {
            return DRAW;
        }
        if (player.isBlackjack()) {
            return WIN;
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

    public GameResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return DRAW;
    }
}
