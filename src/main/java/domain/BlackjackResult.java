package domain;

import domain.dto.BlackjackResultDto;

import java.util.HashMap;
import java.util.Map;

public class BlackjackResult {
    private final Map<String, Boolean> playerWinningMap = new HashMap<>();

    public BlackjackResult(Dealer dealer, Players players) {
        boolean dealerBurst = dealer.isBust();
        int dealerTotal = dealer.getFinalResult();
        for (Player player : players) {
            if (player.isBust() || (!dealerBurst && player.getFinalResult() < dealerTotal)) {
                add(player.getName(), false);
            }
            add(player.getName(), true);
        }
    }

    public void add(String playerName, boolean isWinning) {
        playerWinningMap.put(playerName, isWinning);
    }

    public BlackjackResultDto toResultDto() {
        return new BlackjackResultDto(0, 0, this.playerWinningMap);
    }
}
