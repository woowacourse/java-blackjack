package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class GameResult {

    private Map<Player, WinningResult> playerResult;

    public GameResult(Participants participants) {
        playerResult = new HashMap<>();
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
        if (winningResult == WinningResult.DRAW) {
            return winningResult;
        }
        if (winningResult == WinningResult.WIN) {
            return WinningResult.LOSE;
        }
        return WinningResult.WIN;
    }
}
