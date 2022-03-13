package blackjack.domain;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGameResult {

    private final Participants participants;
    private Map<Player, WinningResult> playerResult;

    public BlackjackGameResult(Participants participants) {
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
            WinningResult convertedResult = winningResult.convertResult();
            dealerResult.put(convertedResult, dealerResult.getOrDefault(convertedResult, 0) + 1);
        }
        return dealerResult;
    }

}
