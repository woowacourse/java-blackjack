package blackjack.domain.game;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.Map;

public class Referee {
    private final GameResults values;

    public Referee() {
        this.values = new GameResults();
    }

    public void calculatePlayersResults(Players players, Dealer dealer) {
        players.forEach(player ->
                values.put(player.getName(), judgeGameResult(dealer, player))
        );
    }

    private GameResult judgeGameResult(Dealer dealer, Player player) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }
        if (isPlayerWin(dealer, player)) {
            return GameResult.WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return GameResult.PUSH;
        }
        return GameResult.LOSE;
    }

    private boolean isPlayerWin(Dealer dealer, Player player) {
        return player.getScore() > dealer.getScore() ||
                player.isBlackjack() ||
                dealer.isBust();
    }

    public Map<String, GameResult> getPlayersResults() {
        return values.getPlayersResults();
    }

    public Map<GameResult, Integer> getDealerResult() {
        return values.getDealerResult();
    }
}
