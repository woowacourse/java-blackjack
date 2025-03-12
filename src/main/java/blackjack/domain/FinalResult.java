package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinalResult {

    public Map<GameResult, Integer> createDealerFinalResult(final Dealer dealer, final List<Player> players) {
        Map<GameResult, Integer> gameFinalResult = new HashMap<>();
        for (Player player : players) {
            GameResult result = GameResult.checkDealerWin(player, dealer);
            gameFinalResult.put(result, gameFinalResult.getOrDefault(result, 0) + 1);
        }
        return gameFinalResult;
    }

    public GameResult createPlayerFinalResult(final Player player, final Dealer dealer) {
        return GameResult.checkPlayerWin(player, dealer);
    }
}
