package bet;

import java.util.Map;
import player.Participant;
import result.MatchResult;

public class BetManager {
    private final Bets bets;
    private final BetResults betResults;

    public BetManager() {
        bets = new Bets();
        betResults = new BetResults();
    }

    public void addInitialBet(Participant participant, int amount) {
        bets.addBet(participant, amount);
    }

    public void calculateParticipantBetResults(Map<Participant, MatchResult> matchResults) {
        matchResults.forEach((participant, matchResult) -> betResults.addBetResult(participant, new BetResult(matchResult, bets.getAmount(participant))));
    }

    public int calculateDealerBetResultAmount() {
        return betResults.calculateDealerBettingResult();
    }

    public Map<Participant, BetResult> getBetResults() {
        return betResults.getBetResults();
    }
}
