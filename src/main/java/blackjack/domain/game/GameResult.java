package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class GameResult {

    private final Dealer dealer;
    private final Map<Player, WinningResult> playerResult;

    public GameResult(Participants participants) {
        dealer = participants.getDealer();
        playerResult = new HashMap<>();
        for (Player player : participants.getPlayers()) {
            playerResult.put(player, WinningResult.of(dealer, player));
        }
    }

    public Map<Player, WinningResult> getPlayerResult() {
        return playerResult;
    }

    public Map<WinningResult, Integer> getDealerResult() {
        Map<WinningResult, Integer> dealerResult = new HashMap<>();
        for (WinningResult winningResult : playerResult.values()) {
            WinningResult convertedResult = winningResult.reverse();
            dealerResult.put(convertedResult, dealerResult.getOrDefault(convertedResult, 0) + 1);
        }
        return dealerResult;
    }
}
