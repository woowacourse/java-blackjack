package bet;

import java.util.Map;
import player.Player;
import result.MatchResult;

public class BetManager {
    private final Bets bets;
    private final BetResults betResults;

    public BetManager() {
        bets = new Bets();
        betResults = new BetResults();
    }

    public void addInitialBet(Player player, int amount) {
        bets.addBet(player, amount);
    }

    public void calculateParticipantBetResults(Map<Player, MatchResult> matchResults) {
        matchResults.forEach((player, matchResult) -> betResults.addBetResult(player, new BetResult(matchResult, bets.getAmount(player))));
    }

    public int calculateDealerBetResultAmount() {
        return betResults.calculateDealerBettingResult();
    }

    public Map<Player, BetResult> getBetResults() {
        return betResults.getBetResults();
    }
}
