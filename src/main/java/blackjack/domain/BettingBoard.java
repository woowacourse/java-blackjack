package blackjack.domain;

import blackjack.domain.money.BettingMoney;
import blackjack.domain.player.Player;

import java.util.HashMap;
import java.util.Map;

public class BettingBoard {
    
    private final Map<Player, BettingMoney> bettingBoard;
    
    public BettingBoard() {
        this.bettingBoard = new HashMap<>();
    }
    
    public void bet(Player player, int bettingMoney) {
        this.bettingBoard.put(player, new BettingMoney(bettingMoney));
    }
    
    public int getProfit(Player player, WinningStatus winningStatus) {
        return bettingBoard.get(player).getProfit(winningStatus);
    }
    
    public int getDealerProfit(Map<Player, WinningStatus> playerWinningStatus) {
        return (-1) * playerWinningStatus.keySet().stream()
                .mapToInt(player -> getProfit(player, playerWinningStatus.get(player)))
                .sum();
    }
}
