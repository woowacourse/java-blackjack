package domain.result;

import domain.participant.BetMap;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.HashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<String, MatchCase> playerResultMap = new HashMap<>();
    private final BetMap betMap;
    private final DealerMatchCount dealerMatchCount;

    private BlackjackResult(Dealer dealer, Players players, BetMap betMap) {
        this.betMap = betMap;
        this.dealerMatchCount = new DealerMatchCount();
        calculateMatchResult(dealer, players);
    }

    private static boolean isPlayerLose(Player player, boolean dealerBurst, int dealerTotal) {
        return player.isBust() || (!dealerBurst && player.getFinalScore() < dealerTotal);
    }

    public static BlackjackResult from(Dealer dealer, Players players, BetMap betMap) {
        return new BlackjackResult(dealer, players, betMap);
    }

    private void calculateMatchResult(Dealer dealer, Players players) {
        boolean dealerBurst = dealer.isBust();
        int dealerTotal = dealer.getFinalScore();

        for (Player player : players) {
            determinePlayerResult(player, dealerBurst, dealerTotal);
        }
    }

    private void determinePlayerResult(Player player, boolean dealerBust, int dealerTotal) {
        if (isPlayerLose(player, dealerBust, dealerTotal)) {
            addMatchResult(player.getName(), MatchCase.LOSE);
            return;
        }
        if (isPlayerScoreEqualsDealer(player, dealerBust, dealerTotal)) {
            addMatchResult(player.getName(), MatchCase.DRAW);
            return;
        }
        addMatchResult(player.getName(), MatchCase.WIN);
    }

    private boolean isPlayerScoreEqualsDealer(Player player, boolean dealerBust, int dealerTotal) {
        return !(player.isBust() || dealerBust) && (player.getFinalScore() == dealerTotal);
    }

    private void addMatchResult(String playerName, MatchCase matchCase) {
        playerResultMap.put(playerName, matchCase);
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

    public int getDealerWinningCount() {
        return this.dealerMatchCount.getWinningCount();
    }

    public int getDealerLoseCount() {
        return this.dealerMatchCount.getLoseCount();
    }

    public int getDrawCount() {
        return this.dealerMatchCount.getDrawCount();
    }

    public Map<String, MatchCase> getPlayerResultMap() {
        return Map.copyOf(playerResultMap);
    }
}
