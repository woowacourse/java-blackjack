package blackjack.model.game;

import blackjack.model.player.Player;
import java.util.Map;

public enum Result {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Result(final String name) {
        this.name = name;
    }

    public static Result findWinner(final Player player, final Player challenger, final Rule rule) {
        if (rule.isDraw(player, challenger)) {
            return DRAW;
        }
        if (rule.getWinner(player, challenger).equals(player)) {
            return WIN;
        }
        return LOSE;
    }

    public static Map<Result, Integer> getResultBoard() {
        return Map.of(
                WIN, 0,
                DRAW, 0,
                LOSE, 0
        );
    }

    public String getName() {
        return name;
    }
}
