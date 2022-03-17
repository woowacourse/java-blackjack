package blackJack.domain.result;

import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGameResult {

    private final Dealer dealer;
    private final List<Player> players;

    public BlackJackGameResult(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<String, Integer> calculateDealerResult() {
        final Map<String, Integer> gameResult = initializeDealerResult();
        for (Player player : players) {
            final BlackJackMatch playerBlackJackMatch = BlackJackMatch.calculateMatch(player, dealer);
            final String matchResult = BlackJackMatch.swapResult(playerBlackJackMatch).getResult();
            gameResult.computeIfPresent(matchResult, (k, v) -> v + 1);
        }
        return gameResult;
    }

    private Map<String, Integer> initializeDealerResult() {
        final Map<String, Integer> gameResult = new LinkedHashMap<>();
        for (BlackJackMatch blackJackMatch : BlackJackMatch.values()) {
            gameResult.put(blackJackMatch.getResult(), 0);
        }
        return gameResult;
    }

    public Map<Player, BlackJackMatch> calculatePlayersResult() {
        final Map<Player, BlackJackMatch> gameResult = new LinkedHashMap<>();
        for (Player player : players) {
            final BlackJackMatch match = BlackJackMatch.calculateMatch(player, dealer);
            gameResult.put(player, match);
        }
        return gameResult;
    }

    public double calculateDealerProfit() {
        final Map<Player, BlackJackMatch> playersResult = calculatePlayersResult();
        double dealerProfit = 0;
        for (Player player : playersResult.keySet()) {
            dealerProfit -= player.calculateProfit(dealer);
        }
        return dealerProfit;
    }
}
