package blackjack.domain.result;

import blackjack.domain.game.Player;

public class PlayerResult {
    private final Player player;
    private final GameResult gameResult;
    private final Score score;

    public PlayerResult(Player player, GameResult gameResult, Score score) {
        this.player = player;
        this.gameResult = gameResult;
        this.score = score;
    }

    public boolean isResultOf(Player player) {
        return player == this.player;
    }

    public boolean isBlackjack() {
        return score.isBlackJack();
    }

    public boolean isWinByBlackjack() {
        return gameResult.isWinByBlackJack();
    }

    public boolean isWinByNotBlackjack() {
        return gameResult.isWinByNotBlackJack();
    }

    public boolean isTie() {
        return gameResult.isTie();
    }

    public boolean isLose() {
        return gameResult.isLose();
    }

    public Player getPlayer() {
        return player;
    }

    public Score getScore() {
        return score;
    }
}
