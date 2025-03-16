package blackjack.domain.result;

import blackjack.domain.game.Player;

public class PlayerResult {
    private final Player player;
    private final GameResultType gameResultType;
    private final Score score;

    public PlayerResult(Player player, GameResultType gameResultType, Score score) {
        this.player = player;
        this.gameResultType = gameResultType;
        this.score = score;
    }

    public boolean isResultOf(Player player) {
        return player == this.player;
    }

    public boolean isBlackjack() {
        return score.isBlackJack();
    }

    public GameResultType getGameResultType() {
        return gameResultType;
    }

    public Player getPlayer() {
        return player;
    }

    public Score getScore() {
        return score;
    }
}
