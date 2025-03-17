package model.betting;

import java.util.HashMap;
import java.util.Map;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;
import model.winning.ParticipantWinningResult;
import model.winning.WinningResult;

public class BettingResult {
    private final PlayersBetting playersBetting;
    private final ParticipantWinningResult participantWinningResult;

    public BettingResult(PlayersBetting playersBetting, ParticipantWinningResult participantWinningResult) {
        this.playersBetting = playersBetting;
        this.participantWinningResult = participantWinningResult;
    }

    public Map<Player, Integer> calculatePlayerBettingResult(Players players, Dealer dealer) {
        Map<Player, Integer> playerBetResults = new HashMap<>();
        for (Player player : players.getPlayers()) {
            int calculateFinalBettingResult = calculateFinalBettingResult(player, dealer);
            playerBetResults.put(player, calculateFinalBettingResult);
        }
        return playerBetResults;
    }

    public int calculateDealerFinalResult(Map<Player, Integer> playerBetResults) {
        int totalPlayerEarnings = playerBetResults.values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        return totalPlayerEarnings * -1;
    }

    private int calculateFinalBettingResult(Player player, Dealer dealer) {
        boolean playerSurrenderCheck = playersBetting.checkPlayerSurrender(player);
        if (playerSurrenderCheck) {
            return playersBetting.computeSurrenderResult(player);
        }
        WinningResult winningResult = participantWinningResult.getPlayerGameResult(player);
        int playerBetResult = playersBetting.computeResultByWinningStatus(player, winningResult);
        int insuranceBetResult = playersBetting.computeInsuranceResult(player, dealer);
        return playerBetResult + insuranceBetResult;
    }
}

