package blackjack.domain.game;

import java.util.HashMap;
import java.util.LinkedHashMap;
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

    public Map<Participant, Integer> calculateTotalProfitResult() {
        Map<Participant, Integer> bettingResult = calculatePlayerBettingProfitResult();
        int playerProfitSum = bettingResult.values().stream()
            .mapToInt(Integer::intValue)
            .sum();
        bettingResult.put(dealer, -playerProfitSum);
        return bettingResult;
    }

    public Map<Participant, Integer> calculatePlayerBettingProfitResult() {
        Map<Participant, Integer> bettingResult = new LinkedHashMap<>();
        bettingResult.put(dealer, 0);
        for (Map.Entry<Player, PlayerWinningResult> entry : playerResult.entrySet()) {
            Player player = entry.getKey();
            bettingResult.put(player, player.calculateProfit(entry.getValue()));
        }
        return bettingResult;
    }

    public Map<Player, PlayerWinningResult> getPlayerResult() {
        return playerResult;
    }
}
