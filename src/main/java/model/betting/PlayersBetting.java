package model.betting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.participant.Dealer;
import model.participant.Player;
import model.turn.PlayerTurn;
import model.winning.WinningResult;

public class PlayersBetting {
    private final Map<Player, Betting> playerBets ;

    private PlayersBetting(List<PlayerTurn> playersTurn) {
        this.playerBets  = createPlayersBetting(playersTurn) ;
    }

    public static PlayersBetting from(List<PlayerTurn> playersTurn){
        return new PlayersBetting(playersTurn);
    }

    private Map<Player, Betting> createPlayersBetting(List<PlayerTurn> playerTurns){
        Map<Player, Betting> playersBet = new HashMap<>();
        for (PlayerTurn playerTurn : playerTurns) {
            playerTurn.putBetting(playersBet);
        }
        return playersBet;
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
