package blackjack.domain.game;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Players;

import java.util.Map;

public class Referee {
    private final GameResults values;

    public Referee() {
        this.values = new GameResults();
    }

    public void calculatePlayersResults(Players players, Dealer dealer) {
        players.forEach(player ->
                values.put(player.getName(), dealer.judgeGameResult(player))
        );
    }

    public Map<String, GameResult> getPlayersResults() {
        return values.getPlayersResults();
    }

    public Map<GameResult, Integer> getDealerResult() {
        return values.getDealerResult();
    }
}
