package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

import java.util.*;

public class GameResult {
    private final Map<String, ResultMatcher> playerGameResults = new LinkedHashMap<>();

    public GameResult(List<Player> players, Dealer dealer) {
        calculatePlayersScore(players, dealer);
    }

    private void calculatePlayersScore(List<Player> players, Dealer dealer) {
        for (Player player : players) {
            playerGameResults.put(player.getPlayerName(), matchResult(player, dealer));
        }
    }

    private ResultMatcher matchResult(Player player, Dealer dealer) {
        return ResultMatcher.calculateResult(player.getTotalScore(), dealer.getTotalScore());
    }

    public EnumMap<ResultMatcher, Integer> calculateDealerScore(Map<String, ResultMatcher> playerGameResults) {
        EnumMap<ResultMatcher, Integer> dealerScore = new EnumMap<>(ResultMatcher.class);

        Arrays.stream(ResultMatcher.values())
                .forEach(resultMatcher -> dealerScore.put(resultMatcher, 0));
        for (ResultMatcher playerResult : playerGameResults.values()) {
            ResultMatcher dealerResult = ResultMatcher.ofOppositeResult(playerResult);
            dealerScore.put(dealerResult, dealerScore.get(dealerResult) + 1);
        }
        return dealerScore;
    }

    public Map<String, ResultMatcher> getPlayerGameResults() {
        return this.playerGameResults;
    }
}
