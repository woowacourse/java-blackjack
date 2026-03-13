package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<String, MatchCase> playerResultMap = new LinkedHashMap<>();
    private final DealerMatchCount dealerMatchCount = new DealerMatchCount();

    private BlackjackResult(Dealer dealer, Players players) {
        calculateMatchResult(dealer, players);
    }

    public static BlackjackResult from(Dealer dealer, Players players) {
        return new BlackjackResult(dealer, players);
    }

    private void calculateMatchResult(Dealer dealer, Players players) {
        for (Player player : players) {
            determinePlayerResult(dealer, player);
        }
    }

    private void determinePlayerResult(Dealer dealer, Player player) {
        MatchJudge matchJudge = new MatchJudge(dealer, player);
        MatchCase matchCase = matchJudge.judge();
        playerResultMap.put(player.getName(), matchCase);
        matchCase.increaseMatchCountOf(this);
    }

    public void increaseDealerWinCount() {
        this.dealerMatchCount.increaseWinCount();
    }

    public void increaseDrawCount() {
        this.dealerMatchCount.increaseDrawCount();
    }

    public void increaseDealerLoseCount() {
        this.dealerMatchCount.increaseLoseCount();
    }

    public Map<String, MatchCase> getPlayerResultMap() {
        return Map.copyOf(playerResultMap);
    }
}
