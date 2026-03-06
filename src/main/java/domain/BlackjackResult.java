package domain;

import domain.dto.BlackjackResultDto;

import java.util.HashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<String, MatchCase> playerWinningMap = new HashMap<>();
    private int winningCount = 0;
    private int drawCount = 0;
    private int loseCount = 0;

    private BlackjackResult(Dealer dealer, Players players) {
        calculateMatchResult(dealer, players);
    }

    private static boolean isPlayerLose(Player player, boolean dealerBurst, int dealerTotal) {
        return player.isBust() || (!dealerBurst && player.getFinalResult() < dealerTotal);
    }

    public static BlackjackResult from(Dealer dealer, Players players) {
        return new BlackjackResult(dealer, players);
    }

    private void calculateMatchResult(Dealer dealer, Players players) {
        boolean dealerBurst = dealer.isBust();
        int dealerTotal = dealer.getFinalResult();

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
        return !(player.isBust() || dealerBust) && (player.calculateScore() == dealerTotal);
    }

    private void addMatchResult(String playerName, MatchCase matchCase) {
        playerWinningMap.put(playerName, matchCase);
        increaseMatchResult(matchCase);
    }

    private void increaseMatchResult(MatchCase matchCase) {
        if (matchCase == MatchCase.WIN) {
            loseCount++;
            return;
        }

        if (matchCase == MatchCase.DRAW) {
            drawCount++;
            return;
        }

        winningCount++;
    }

    public BlackjackResultDto toResultDto() {
        return new BlackjackResultDto(
                this.winningCount,
                this.drawCount,
                this.loseCount,
                Map.copyOf(this.playerWinningMap)
        );
    }
}
