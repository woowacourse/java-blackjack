package blackjack.model.game;

import java.util.Map;

import blackjack.model.player.Player;

public enum Result {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Result(final String name) {
        this.name = name;
    }

    public static Result findWinner(final Player player, final Player challenger, final BlackJackRule blackJackRule) {
        if (blackJackRule.isDraw(player, challenger)) {
            return DRAW;
        }
        if (blackJackRule.getWinner(player, challenger).equals(player)) {
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
