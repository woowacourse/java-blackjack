package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    private Map<PlayerResult, List<Player>> playerResults;

    private GameResult(Map<PlayerResult, List<Player>> playerResults) {
        this.playerResults = playerResults;
    };

    public static GameResult calculate(List<Player> players, Dealer dealer) {
        Map<PlayerResult, List<Player>> playerResults = players.stream()
                .collect(Collectors.groupingBy(player -> PlayerResult.match(dealer, player), Collectors.toList()));

        for(PlayerResult playerResult : PlayerResult.values()) {
            playerResults.putIfAbsent(playerResult, new ArrayList<>());
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
