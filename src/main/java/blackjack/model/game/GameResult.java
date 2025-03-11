package blackjack.model.game;

import java.util.Map;

import blackjack.model.player.Player;

public enum GameResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    GameResult(final String name) {
        this.name = name;
    }

    public static GameResult calculateResult(final Player player, final Player challenger,
                                             final BlackJackRule blackJackRule
    ) {
        if (blackJackRule.isDraw(player, challenger)) {
            return DRAW;
        }
        if (blackJackRule.getWinner(player, challenger).equals(player)) {
            return WIN;
        }
        return LOSE;
    }

    public static Map<GameResult, Integer> getResultBoard() {
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
