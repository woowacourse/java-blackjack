package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.domain.score.GameResult;
import blackjack.domain.score.Score;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResultBoard {
    private final Map<Player, GameResult> resultBoard = new HashMap<>();

    public GameResultBoard(Dealer dealer, List<Player> players) {
        Score dealerScore = dealer.calculateScore();
        for (Player player : players) {
            Score playerScore = player.calculateScore();
            GameResult gameResult = calculateGameResult(playerScore, dealerScore);
            resultBoard.put(player, gameResult);
        }
    }

    public GameResult findByPlayer(Player player) {
        return resultBoard.get(player);
    }

    public Map<Player, GameResult> getResultBoard() {
        return resultBoard;
    }

    private GameResult calculateGameResult(Score playerScore, Score dealerScore) {
        GameResult gameResult = playerScore.compete(dealerScore);
        if (playerScore.isBusted()) {
            gameResult = GameResult.LOSE;
        }
        if (playerScore.isBlackJack()) {
            gameResult = GameResult.WIN_BY_BLACK_JACK;
        }
        return gameResult;
    }
}
