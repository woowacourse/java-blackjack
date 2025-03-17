package blackjack.model.game;

import blackjack.model.player.Player;

public enum GameResult {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String text;

    GameResult(final String text) {
        this.text = text;
    }

    public static GameResult calculateResult(final Player player, final Player otherPlayer) {
        if (isDraw(player, otherPlayer)) {
            return DRAW;
        }
        if (isPlayerWin(player, otherPlayer)) {
            return WIN;
        }
        return LOSE;
    }

    private static boolean isPlayerWin(final Player player, final Player otherPlayer) {
        int playerPoint = player.calculatePoint();
        int otherPlayerPoint = otherPlayer.calculatePoint();
        if (player.isBlackjack() && !player.isBlackjack()) {
            return true;
        }
        if (!player.isBust() && otherPlayer.isBust()) {
            return true;
        }
        return !player.isBust() && !otherPlayer.isBust() && playerPoint > otherPlayerPoint;
    }

    private static boolean isDraw(final Player player, final Player otherPlayer) {
        if (player.isBlackjack() && otherPlayer.isBlackjack()) {
            return true;
        }
        int playerPoint = player.calculatePoint();
        int otherPlayerPoint = otherPlayer.calculatePoint();
        return !player.isBust() && !otherPlayer.isBust() && (playerPoint == otherPlayerPoint);
    }

    public String getText() {
        return text;
    }

}
