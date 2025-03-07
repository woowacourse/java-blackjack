package domain;

import domain.gamer.Dealer;
import domain.gamer.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameManager {

    private final Dealer dealer;
    private final List<Player> players;

    public GameManager(final Dealer dealer, final List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static GameManager create(final Dealer dealer, final List<Player> players) {
        dealer.receiveCard(2);
        for (Player player : players) {
            player.receiveCard(2);
        }
        return new GameManager(dealer, players);
    }


    public Map<GameResult, Integer> calculateDealerGameResult() {
        final List<GameResult> playerGameResult = calculatePlayerGameResult().values().stream().toList();
        return GameResult.getAllGameResults().stream()
                .filter(playerGameResult::contains)
                .collect(Collectors.toMap(
                        GameResult::swapGameResult,
                        result -> Collections.frequency(playerGameResult, result),
                        (newResult, oldResult) -> oldResult
                ));
    }

    private GameResult calculateResult(final Player player) {
        if (dealer.isBust() && player.isBust()) {
            return GameResult.DRAW;
        }
        if (dealer.isBust()) {
            return GameResult.WIN;
        }
        return player.calculateGameResult(dealer.calculateScore());
    }

    public Map<String, GameResult> calculatePlayerGameResult() {
        Map<String, GameResult> resultMap = new HashMap<>();
        for (Player player : players) {
            resultMap.put(player.getName(), calculateResult(player));
        }
        return resultMap;
    }
}
