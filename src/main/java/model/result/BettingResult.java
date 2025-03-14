package model.result;

import java.util.HashMap;
import java.util.Map;
import model.Betting;
import model.participant.Dealer;
import model.participant.Player;
import model.participant.Players;

public class BettingResult {
    private final Map<Player, Betting> betting;
    private final ParticipantWinningResult participantWinningResult;

    public BettingResult(Map<Player, Betting> betting, ParticipantWinningResult participantWinningResult) {
        this.betting = betting;
        this.participantWinningResult = participantWinningResult;
    }

    public Map<Player, Integer> calculatePlayerBettingResult(Players players, Dealer dealer) {
        Map<Player, Integer> playerBetResults = new HashMap<>();
        for (Player player : players.getPlayers()) {
            WinningResult winningResult = participantWinningResult.getPlayerGameResult(player);
            int playerBetResult = computeResultByWinningStatus(player, winningResult);
            int insuranceBetResult = computeInsuranceResult(player, dealer);
            playerBetResults.put(player, playerBetResult + insuranceBetResult);
        }
        return playerBetResults;
    }

    public int calculateDealerFinalResult(Map<Player, Integer> playerBetResults) {
        return playerBetResults.values().stream().mapToInt(i -> -i).sum();
    }

    private int computeResultByWinningStatus(Player player, WinningResult winningResult) {
        Betting bet = betting.get(player);
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
    private int computeInsuranceResult(Player player, Dealer dealer){
        Betting bet = betting.get(player);
        return bet.calculateInsurance(dealer);
    }
}

