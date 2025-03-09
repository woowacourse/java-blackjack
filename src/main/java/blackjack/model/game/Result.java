package blackjack.model.game;

import blackjack.model.player.Player;
import java.util.List;

public enum Result {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Result(final String name) {
        this.name = name;
    }

    public static Result findWinner(final Player player, final Player challenger) {
        if (player.isDraw(challenger)) {
            return DRAW;
        }
        if (player.isWin(challenger)) {
            return WIN;
        }
        return LOSE;
    }

    public static List<Result> getSortedResult() {
        return List.of(WIN, DRAW, LOSE);
    }

    public String getName() {
        return name;
    }
}
