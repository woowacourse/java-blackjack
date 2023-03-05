package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<Player, ResultMatcher> playerGameResults = new LinkedHashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        calculatePlayersScore(players, dealer);
    }

    private void calculatePlayersScore(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            playerGameResults.put(player, matchResult(player, dealer));
        }
    }

    private ResultMatcher matchResult(Player player, Dealer dealer) {
        return ResultMatcher.calculateResult(player.getTotalScore(), dealer.getTotalScore());
    }

    public EnumMap<ResultMatcher, Integer> calculateDealerScore(Map<Player, ResultMatcher> playerGameResults) {
        EnumMap<ResultMatcher, Integer> dealerScore = new EnumMap<>(ResultMatcher.class);
        for (ResultMatcher resultMatcher : ResultMatcher.values()) {
            dealerScore.put(resultMatcher, 0);
        }
        for (ResultMatcher playerResult : playerGameResults.values()) {
            ResultMatcher dealerResult = ResultMatcher.ofOppositeResult(playerResult);
            dealerScore.put(dealerResult, dealerScore.get(dealerResult) + 1);
        }
        return dealerScore;
    }

    public Map<Player, ResultMatcher> getPlayerGameResults() {
        return this.playerGameResults;
    }
}
