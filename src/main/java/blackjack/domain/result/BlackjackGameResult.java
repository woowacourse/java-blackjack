package blackjack.domain.result;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGameResult {

    private final Dealer dealer;
    private final List<Player> players;

    public BlackjackGameResult(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public Map<String, Integer> calculateDealerResult() {
        final Map<String, Integer> gameResult = initializeDealerResult();
        for (Player player : players) {
            final BlackjackMatch match = dealer.isWin(player);
            gameResult.computeIfPresent(match.getResult(), (k, v) -> v + 1);
        }
        return gameResult;
    }

    private Map<String, Integer> initializeDealerResult() {
        final Map<String, Integer> gameResult = new LinkedHashMap<>();
        for (BlackjackMatch blackjackMatch : BlackjackMatch.values()) {
            gameResult.put(blackjackMatch.getResult(), 0);
        }
        return gameResult;
    }

    public Map<Player, BlackjackMatch> calculatePlayersResult() {
        final Map<Player, BlackjackMatch> gameResult = new LinkedHashMap<>();
        for (Player player : players) {
            final BlackjackMatch match = player.isWin(dealer);
            gameResult.put(player, match);
        }
        return gameResult;
    }
}
