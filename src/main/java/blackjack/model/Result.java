package blackjack.model;

public enum Result {

    WIN,
    DRAW,
    LOSE;

    public static Result findWinner(final Player player, final Player otherPlayer, final Rule rule) {
        if (rule.isDraw(player, otherPlayer)) {
            return DRAW;
        }
        if (rule.getWinner(player, otherPlayer).equals(player)) {
            return WIN;
        }
        return LOSE;
    }

}
