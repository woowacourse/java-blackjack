package blackjack.model.game;

import blackjack.model.player.Player;

public enum GameResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    GameResult(final String name) {
        this.name = name;
    }

    public static GameResult calculateResult(final Player player, final Player otherPlayer,
                                             final BlackJackRule blackJackRule
    ) {
        if (blackJackRule.isDraw(player, otherPlayer)) {
            return DRAW;
        }
        if (isPlayerWin(player, otherPlayer, blackJackRule)) {
            return WIN;
        }
        return LOSE;
    }

    private static boolean isPlayerWin(final Player player, final Player otherPlayer,
                                       final BlackJackRule blackJackRule
    ) {
        int playerPoint = blackJackRule.calculateOptimalPoint(player);
        int otherPlayerPoint = blackJackRule.calculateOptimalPoint(otherPlayer);
        if (blackJackRule.isBlackjack(player) && !blackJackRule.isBlackjack(otherPlayer)) {
            return true;
        }
        if (!blackJackRule.isBust(playerPoint) && blackJackRule.isBust(otherPlayerPoint)) {
            return true;
        }
        return !blackJackRule.isBust(playerPoint) && !blackJackRule.isBust(otherPlayerPoint)
                && playerPoint > otherPlayerPoint;
    }

    public String getName() {
        return name;
    }

}
