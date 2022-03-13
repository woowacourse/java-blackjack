package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGameResult {

    private final Map<Player, MatchResult> gameResult;

    private BlackJackGameResult(Map<Player, MatchResult> gameResult) {
        this.gameResult = gameResult;
    }

    public static BlackJackGameResult ofGameResult(Dealer dealer, List<Player> players) {
        final Map<Player, MatchResult> gameResult = new LinkedHashMap<>();

        for (Player player : players) {
            final MatchResult winOrLose = player.getMatchResult(dealer);
            gameResult.put(player, winOrLose);
        }

        return new BlackJackGameResult(gameResult);
    }

    public Map<MatchResult, Integer> calculateDealerResult() {
        final Map<MatchResult, Integer> dealerGameResult = getDealerMatchResult();

        for (MatchResult value : gameResult.values()) {
            dealerGameResult.computeIfPresent(value, (k, v) -> v + 1);
        }
        swapResult(dealerGameResult);

        return dealerGameResult;
    }

    private Map<MatchResult, Integer> getDealerMatchResult() {
        final Map<MatchResult, Integer> dealerGameResult = new EnumMap<>(MatchResult.class);

        for (MatchResult value : MatchResult.values()) {
            dealerGameResult.put(value, 0);
        }

        return dealerGameResult;
    }

    private void swapResult(Map<MatchResult, Integer> dealerGameResult) {
        final int loseCounts = dealerGameResult.get(MatchResult.WIN);
        dealerGameResult.put(MatchResult.WIN, dealerGameResult.get(MatchResult.LOSE));
        dealerGameResult.put(MatchResult.LOSE, loseCounts);
    }

    public Map<Player, MatchResult> getGameResult() {
        return gameResult;
    }
}
