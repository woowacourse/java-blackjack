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

    private void calculateMatchResult(Dealer dealer, Players players) {
        boolean dealerBurst = dealer.isBust();
        int dealerTotal = dealer.getFinalResult();

        for (Player player : players) {
            determinePlayerResult(player, dealerBurst, dealerTotal);
        }
    }

    private void determinePlayerResult(Player player, boolean dealerBurst, int dealerTotal) {
        if (player.isBust() || (!dealerBurst && player.getFinalResult() < dealerTotal)) {
            addMatchResult(player.getName(), MatchCase.WIN);
        }
        addMatchResult(player.getName(), MatchCase.LOSE);
    }

    public static BlackjackResult from(Dealer dealer, Players players) {
        return new BlackjackResult(dealer, players);
    }

    private void addMatchResult(String playerName, MatchCase matchCase) {
        playerWinningMap.put(playerName, matchCase);
    }

    public BlackjackResultDto toResultDto() {
        return new BlackjackResultDto(
                this.winningCount,
                this.loseCount,
                Map.copyOf(this.playerWinningMap)
        );
    }
}
