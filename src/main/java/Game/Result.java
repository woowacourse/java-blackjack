package Game;

import domain.Dealer;
import domain.Gambler;
import domain.Player;
import domain.Players;

import java.util.LinkedHashMap;
import java.util.Map;

import static Game.Referee.decideWinner;

public class Result {

    private final Map<Gambler, Integer> result;
    private final Players players;
    private final Dealer dealer;

    public Result(Players players, Dealer dealer) {
        this.players = players;
        this.dealer = dealer;
        result = createResult();
    }

    private Map<Gambler, Integer> createResult() {
        Map<Gambler, Integer> result = new LinkedHashMap<>();
        return calculateWinCount(result);
    }

    private Map<Gambler, Integer> calculateWinCount(Map<Gambler, Integer> result) {
        result.put(dealer, 0);
        for (Player player : players.getPlayers()) {
            decideWinner(player, dealer, result);
        }
        return result;
    }

    public Map<Gambler, Integer> getResult() {
        return result;
    }
}
