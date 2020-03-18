package domain;

import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GameResult {
    private final Map<String, Boolean> playerResult = new HashMap<>();

    public GameResult(Players players, Dealer dealer) {
        makeResult(players, dealer);
    }

    private void makeResult(Players players, Dealer dealer) {
        for (Player player : players.get()) {
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
