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
        Betting playerBetting = playersBetting.findPlayerBet(player);
        if (playerBetting.checkSurrender()) {
            return playerBetting.calculateSurrender();
        }
        WinningResult winningResult = participantWinningResult.getPlayerGameResult(player);
        int playerBetResult = computeResultByWinningStatus(player, winningResult);
        int insuranceBetResult = computeInsuranceResult(player, dealer);
        return playerBetResult + insuranceBetResult;
    }

    private int computeResultByWinningStatus(Player player, WinningResult winningResult) {
        Betting bet = playersBetting.findPlayerBet(player);
        if (winningResult == WinningResult.LOSE) {
            return bet.calculateLose();
        }
        if (winningResult == WinningResult.DRAW) {
            return bet.calculateDraw();
        }
        if (winningResult == WinningResult.WIN && player.checkBlackjack()) {
            return bet.calculateBlackJack();
        }
        return bet.calculateWin();
    }

    private int computeInsuranceResult(Player player, Dealer dealer) {
        Betting bet = playersBetting.findPlayerBet(player);
        return bet.calculateInsurance(dealer);
    }
}

