package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

import java.util.HashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<String, MatchCase> playerResultMap = new HashMap<>();
    private int dealerWinningCount = 0;
    private int drawCount = 0;
    private int dealerLoseCount = 0;

    private BlackjackResult(Dealer dealer, Players players) {
        calculateMatchResult(dealer, players);
    }

    private static boolean isPlayerLose(Player player, boolean dealerBurst, int dealerTotal) {
        return player.isBust() || (!dealerBurst && player.getFinalScore() < dealerTotal);
    }

    public static BlackjackResult from(Dealer dealer, Players players) {
        return new BlackjackResult(dealer, players);
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
        this.dealerWinningCount++;
    }

    public void increaseDrawCount() {
        this.drawCount++;
    }

    public void increaseDealerLoseCount() {
        this.dealerLoseCount++;
    }

    public int getDealerWinningCount() {
        return dealerWinningCount;
    }

    public int getDealerLoseCount() {
        return dealerLoseCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public Map<String, MatchCase> getPlayerResultMap() {
        return Map.copyOf(playerResultMap);
    }
}
