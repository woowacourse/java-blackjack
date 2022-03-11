package blackjack.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGame {

    private final Participants participants;
    private Map<Player, WinningResult> playerResult;

    public BlackjackGame(Participants participants) {
        this.participants = participants;
    }

    public void calculatePlayerResult() {
        playerResult = new LinkedHashMap<>();
        Dealer dealer = participants.getDealer();
        for (Player player : participants.getPlayers()) {
            playerResult.put(player, WinningResult.of(player, dealer));
        }
    }

    public Map<Player, WinningResult> getPlayerResult() {
        return playerResult;
    }

    public Map<WinningResult, Integer> getDealerResult() {
        Map<WinningResult, Integer> dealerResult = new HashMap<>();
        for (WinningResult winningResult : playerResult.values()) {
            WinningResult convertedResult = convertToDealerWinningResult(winningResult);
            dealerResult.put(convertedResult, dealerResult.getOrDefault(convertedResult, 0) + 1);
        }
        return dealerResult;
    }

    private WinningResult convertToDealerWinningResult(WinningResult winningResult) {
        if(winningResult == WinningResult.DRAW) {
            return winningResult;
        }
        if(winningResult == WinningResult.WIN) {
            return WinningResult.LOSE;
        }
        return WinningResult.WIN;
    }
}
