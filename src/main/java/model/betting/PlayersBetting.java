package model.betting;

import java.util.Map;
import model.participant.Dealer;
import model.participant.Player;
import model.winning.WinningResult;

public class PlayersBetting {
    private final Map<Player, Betting> playerBets ;

    public PlayersBetting(Map<Player, Betting> playerBets) {
        this.playerBets  = playerBets ;
    }

    public int computeResultByWinningStatus(Player player, WinningResult winningResult) {
        Betting bet = playerBets.get(player);
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

    public int computeInsuranceResult(Player player, Dealer dealer) {
        Betting bet = playerBets.get(player);
        return bet.calculateInsurance(dealer);
    }

    public int computeSurrenderResult(Player player){
        Betting bet = playerBets.get(player);
        return bet.calculateSurrender();
    }

    public boolean checkPlayerSurrender(Player player){
        return playerBets.get(player).checkSurrender();
    }
}
