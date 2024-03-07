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
                values.put(player.getName(), judgeGameResult(player, dealer))
        );
    }

    private GameResult judgeGameResult(Player player, Dealer dealer) {
        // TODO: 함수 분리
        if (player.isBurst()) {
            return GameResult.LOSE;
        }
        if (player.getScore() > dealer.getScore() ||
                player.isBlackjack() ||
                dealer.isBurst()) {
            return GameResult.WIN;
        }
        if (dealer.getScore() == player.getScore()) {
            return GameResult.DRAW;
        }
        return GameResult.LOSE;
    }

    public Map<String, GameResult> getPlayersResults() {
        return values.getPlayersResults();
    }

    public Map<GameResult, Integer> getDealerResult() {
        return values.getDealerResult();
    }
}
