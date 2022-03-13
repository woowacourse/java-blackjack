package blackJack.domain.result;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

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
        final Map<MatchResult, Integer> dealerGameScore = getWinOrLose();

        for (MatchResult value : gameResult.values()) {
            dealerGameScore.computeIfPresent(value, (k, v) -> v + 1);
        }
        swapResult(dealerGameScore);

        return dealerGameScore;
    }

    private Map<MatchResult, Integer> getWinOrLose() {
        final Map<MatchResult, Integer> dealerGameScore = new EnumMap<>(MatchResult.class);

        for (MatchResult value : MatchResult.values()) {
            dealerGameScore.put(value, 0);
        }

        return dealerGameScore;
    }

    private void swapResult(Map<MatchResult, Integer> dealerGameScore) {
        final int loseCounts = dealerGameScore.get(MatchResult.WIN);
        dealerGameScore.put(MatchResult.WIN, dealerGameScore.get(MatchResult.LOSE));
        dealerGameScore.put(MatchResult.LOSE, loseCounts);
    }

    public Map<Player, MatchResult> getGameResult() {
        return gameResult;
    }
}
