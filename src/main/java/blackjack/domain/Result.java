package blackjack.domain;

import blackjack.domain.player.Player;

public enum Result {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String result;

    Result(String result) {
        this.result = result;
    }

    public static Result findResult(final Player player, final Player otherPlayer) {
        if (player.isBust() && otherPlayer.isBust()) {
            return DRAW;
        }
        if (player.isBust()) {
            return LOSE;
        }
        if (otherPlayer.isBust()) {
            return WIN;
        }
        return compareScore(player.getTotalScore(), otherPlayer.getTotalScore());
    }

    private static Result compareScore(final int myScore, final int otherScore) {
        if (myScore > otherScore) {
            return WIN;
        }
        if (myScore < otherScore) {
            return LOSE;
        }
        return DRAW;
    }

    public String getResult() {
        return result;
    }
}
