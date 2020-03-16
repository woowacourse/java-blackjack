package domain;

import domain.user.Dealer;
import domain.user.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<String, Boolean> playerResult = new HashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        createResult(players, dealer);
    }

    private void createResult(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            playerResult.put(player.getName(), !dealer.isWinner(player));
        }
    }

    public int calculateDealerWins() {
        return (int) playerResult.keySet()
                .stream()
                .map(playerResult::get)
                .filter(check -> !check)
                .count();
    }

    public int calculateDealerLoses() {
        return (int) playerResult.keySet()
                .stream()
                .map(playerResult::get)
                .filter(check -> check)
                .count();
    }

    public Map<String, Boolean> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }
}
