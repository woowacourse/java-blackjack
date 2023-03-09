package domain;

import domain.participants.Dealer;
import domain.participants.Player;
import domain.participants.Players;

import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private static final int BLACK_JACK = 21;
    private static final Double BLACK_JACK_BONUS = 1.5;
    private final Map<Player, Integer> gameResult;

    public GameResult(Players players) {
        this.gameResult = new HashMap<>();
        setPlayersInitialResult(players);
    }

    private void setPlayersInitialResult(Players players) {
        gameResult.put(players.findDealer(), 0);
        for (Player player : players.getPlayersWithOutDealer()) {
            gameResult.put(player, 0);
        }
    }
}
