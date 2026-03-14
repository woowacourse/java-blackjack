package domain;

import dto.GameResult;
import dto.GameStatus;

public enum WinningCondition {
    WIN(1), DRAW(0), LOSE(-1), BLACK_JACK(1.5);

    private final double rate;

    WinningCondition(double rate) {
        this.rate = rate;
    }

    public static WinningCondition from(GameStatus player, GameStatus dealer) {
        if (isBlackJackCase(player, dealer)) {
            return blackJackResult(player, dealer);
        }

        if (isBustCase(player, dealer)) {
            return bustResult(player, dealer);
        }

        return compareResult(player, dealer);
    }

    public double earning(int betAmount) {
        return rate * betAmount;
    }

    private static boolean isBlackJackCase(GameStatus player, GameStatus dealer) {
        return player.hand().isBlackJack() || dealer.hand().isBlackJack();
    }

    private static WinningCondition blackJackResult(GameStatus player, GameStatus dealer) {
        if (isBothBlackJack(player, dealer)) {
            return DRAW;
        }
        return BLACK_JACK;
    }

    private static boolean isBothBlackJack(GameStatus player, GameStatus dealer) {
        return player.isBlackJack() && dealer.isBlackJack();
    }

    private static boolean isBustCase(GameStatus player, GameStatus dealer) {
        return isPlayerBust(player) || isDealerBust(dealer);
    }

    private static WinningCondition bustResult(GameStatus player, GameStatus dealer) {
        if (isPlayerBust(player)) {
            return LOSE;
        }

        return WIN;
    }

    private static boolean isPlayerBust(GameStatus player) {
        return player.isBusted();
    }

    private static WinningCondition compareResult(GameStatus player, GameStatus dealer) {
        if (isDraw(player, dealer)) {
            return DRAW;
        }

        if (isLose(player, dealer)) {
            return LOSE;
        }

        return WIN;
    }

    private static boolean isDealerBust(GameStatus dealer) {
        return dealer.isBusted();
    }

    private static boolean isDraw(GameStatus player, GameStatus dealer) {
        return player.score() == dealer.score();
    }

    private static boolean isLose(GameStatus player, GameStatus dealer) {
        return player.score() < dealer.score();
    }
}
