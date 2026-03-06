package domain;

import domain.dto.BlackjackResultDto;

import java.util.HashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<String, MatchCase> playerWinningMap = new HashMap<>();
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
        playerWinningMap.put(playerName, matchCase);
        increaseMatchResult(matchCase);
    }

    private void increaseMatchResult(MatchCase matchCase) {
        if (matchCase == MatchCase.WIN) {
            dealerLoseCount++;
            return;
        }

        if (matchCase == MatchCase.DRAW) {
            drawCount++;
            return;
        }

        dealerWinningCount++;
    }

    public BlackjackResultDto toResultDto() {
        return new BlackjackResultDto(
                this.dealerWinningCount,
                this.drawCount,
                this.dealerLoseCount,
                Map.copyOf(this.playerWinningMap)
        );
    }
}
