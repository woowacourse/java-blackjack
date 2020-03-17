package blackjack.domain;

import blackjack.domain.strategy.DealerStatusStrategy;

import java.util.*;

public class GameResult {
    private Map<Player, PlayerResult> gameResult;

    private GameResult(Map<Player, PlayerResult> result) {
        this.gameResult = result;
    }

    public static GameResult calculateGameResult(Dealer dealer, Players players) {
        Map<Player, PlayerResult> result = new LinkedHashMap<>();
        players.getPlayers()
                .forEach(player ->
                        result.put(player, calculateResultByDealerStatusStrategy(dealer, player))
                );
        return new GameResult(result);
    }

    private static PlayerResult calculateResultByDealerStatusStrategy(Dealer dealer, Player player) {
        DealerStatusStrategy dealerStatusStrategy = dealer.getStatus().getDealerStatusStrategy();
        return dealerStatusStrategy.calculateResultByPlayerStatus(dealer, player);
    }

    public Map<Player, PlayerResult> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    public String getKoreanName(Player player) {
        return gameResult.get(player).getKoreanName();
    }
}
