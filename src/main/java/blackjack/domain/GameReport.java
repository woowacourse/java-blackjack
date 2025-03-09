package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameReport {

    public Map<GameResult, Integer> getDealerResult(final Dealer dealer, final List<Player> players) {
        Map<GameResult, Integer> gameFinalResult = new HashMap<>();
        for (Player player : players) {
            GameResult result = GameResult.checkDealerWin(player, dealer);
            gameFinalResult.put(result, gameFinalResult.getOrDefault(result, 0) + 1);
        }
        return gameFinalResult;
    }

    public GameResult getPlayerResult(final Player player, final Dealer dealer) {
        return GameResult.checkPlayerWin(player, dealer);
    }
}
