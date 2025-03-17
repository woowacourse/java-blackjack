package model.result;

import model.participant.Dealer;
import model.participant.Player;

public final class PlayerWinningResult {
    private final Player player;
    private final GameResult gameResult;

    public static PlayerWinningResult from(final Dealer dealer, final Player player) {
        if (player.isBurst()) {
            return new PlayerWinningResult(player, GameResult.LOSE);
        }
        if (dealer.isBurst()) {
            return new PlayerWinningResult(player, GameResult.WIN);
        }
        return new PlayerWinningResult(
                player, checkResultIfNotBurst(dealer, player)
        );
    }

    public PlayerWinningResult(final Player player, final GameResult gameResult) {
        this.player = player;
        this.gameResult = gameResult;
    }

    private static GameResult checkResultIfNotBurst(final Dealer dealer, final Player player) {
        int dealerScore = dealer.calculateFinalScore();
        int playerScore = player.calculateFinalScore();

        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }
        if (dealerScore < playerScore) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public boolean isLose() {
        return gameResult == GameResult.LOSE;
    }

    public boolean isBlackjackWin() {
        return player.isBlackjack() && gameResult == GameResult.WIN;
    }

    public Player getPlayer() {
        return player;
    }

    public GameResult getGameResult() {
        return gameResult;
    }
}
