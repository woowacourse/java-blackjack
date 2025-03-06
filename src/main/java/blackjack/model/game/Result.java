package blackjack.model.game;

import blackjack.model.player.Player;

public enum Result {

    WIN,
    DRAW,
    LOSE;

    public static Result findWinner(final Player player, final Player challenger, final Rule rule) {
        if (rule.isDraw(player, challenger)) {
            return DRAW;
        }
        if (rule.getWinner(player, challenger).equals(player)) {
            return WIN;
        }
        return LOSE;
    }

}
