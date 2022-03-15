package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class GameResult {

    private final Dealer dealer;
    private final Map<Player, PlayerWinningResult> playerResult;

    public GameResult(Participants participants) {
        dealer = participants.getDealer();
        playerResult = new HashMap<>();
        for (Player player : participants.getPlayers()) {
            playerResult.put(player, PlayerWinningResult.of(dealer, player));
        }
    }

    public Map<Player, PlayerWinningResult> getPlayerResult() {
        return playerResult;
    }

    public Map<PlayerWinningResult, Integer> getDealerResult() {
        Map<PlayerWinningResult, Integer> dealerResult = new HashMap<>();
        for (PlayerWinningResult winningResult : playerResult.values()) {
            PlayerWinningResult convertedResult = winningResult.reverse();
            dealerResult.put(convertedResult, dealerResult.getOrDefault(convertedResult, 0) + 1);
        }
        return dealerResult;
    }

    public Map<Participant, Double> calculateBettingResult() {
        Map<Participant, Double> bettingResult = new HashMap<>();
        bettingResult.put(dealer, 0.0);
        double dealerProfit = bettingResult.get(dealer);
        for (Player player : playerResult.keySet()) {
            double profit = player.calculateProfit(playerResult.get(player));
            bettingResult.put(player, profit);
            dealerProfit -= profit;
        }
        bettingResult.put(dealer, dealerProfit);
        return bettingResult;
    }
}
