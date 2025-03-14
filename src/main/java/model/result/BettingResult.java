package model.result;

import java.util.Map;
import model.Betting;
import model.participant.Player;
import model.participant.Players;
import model.turn.PlayerTurn;
import model.turn.PlayerTurnManager;

public class BettingResult {
    private final Map<Player, Betting> betting;

    public BettingResult(Map<Player, Betting> betting) {
        this.betting = betting;
    }

    public void calculatePlayerBettingResult(Players players, ParticipantWinningResult participantWinningResult) {
        for (Player player : players.getPlayers()) {
            WinningResult winningResult = participantWinningResult.getPlayerGameResult(player);
            computeResultByWinningStatus(player, winningResult);
        }
    }

    public int calculateDealerFinalResult() {
        return betting.values().stream().mapToInt(i -> -i).sum();
    }

    private void computeResultByWinningStatus(Player player, WinningResult winningResult) {
        int bettingPrice = betting.get(player);
        if (winningResult == WinningResult.LOSE) {
            betting.put(player, -bettingPrice);
        }
        if (winningResult == WinningResult.DRAW) {
            betting.put(player, 0);
        }
        if (winningResult == WinningResult.WIN && player.checkBlackjack()) {
            betting.put(player, bettingPrice * 3 / 2);
        }
    }

    public Map<Player, Integer> getBetting() {
        return betting;
    }
}

