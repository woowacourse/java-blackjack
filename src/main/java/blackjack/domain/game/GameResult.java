package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.game.winningstrategy.WinningStrategy;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class GameResult {

    private Dealer dealer;
    private Map<Player, WinningResult> playerResult;

    public GameResult(Participants participants) {
        dealer = participants.getDealer();
        playerResult = new HashMap<>();
        for (Player player : participants.getPlayers()) {
            playerResult.put(player, WinningResult.NONE);
        }
    }

    public void update(WinningStrategy winningStrategy) {
        for (Player player : playerResult.keySet()) {
            changePlayerResult(player, winningStrategy);
        }
    }

    private void changePlayerResult(Player player, WinningStrategy winningStrategy) {
        if (playerResult.get(player) == WinningResult.NONE) {
            playerResult.put(player, winningStrategy.getResult(dealer, player));
        }
    }

    public boolean isDealerBlackjack() {
        return dealer.isBlackjack();
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
