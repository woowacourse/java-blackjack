package domain;

import domain.user.Dealer;
import domain.user.Player;

import java.util.*;

public class GameResult {
    private Map<PlayerResult, List<Player>> playerResults;

    private GameResult(Map<PlayerResult, List<Player>> playerResults) {
        this.playerResults = playerResults;
    };

    public static GameResult calculate(List<Player> players, Dealer dealer) {
        // todo: refac
        Map<PlayerResult, List<Player>> playerResults = new HashMap<>();
        for (PlayerResult playerResult : PlayerResult.values()) {
            playerResults.put(playerResult, new ArrayList<>());
        }

        for (Player player : players) {
            PlayerResult playerResult = PlayerResult.match(dealer, player);
            // todo: refac, remove get
            playerResults.get(playerResult).add(player);

        }

        return new GameResult(playerResults);
    }

    public int sizeOfDealerWins() {
        return playerResults.get(PlayerResult.LOSE)
                            .size();
    }

    public int sizeOfDraws() {
        return playerResults.get(PlayerResult.DRAW)
                            .size();
    }

    public int sizeOfDealerLosses() {
        return playerResults.get(PlayerResult.WIN)
                            .size();
    }

    public List<Player> getPlayersOf(PlayerResult playerResult) {
        return playerResults.get(playerResult);
    }
}
