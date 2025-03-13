package blackjack.domain;

import blackjack.domain.money.BettingMoney;
import blackjack.domain.money.Money;
import blackjack.domain.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BettingBoard {
    
    private final Map<Player, BettingMoney> bettingBoard;
    
    public BettingBoard() {
        this.bettingBoard = new HashMap<>();
    }
    
    public void bet(Player player, int bettingMoney) {
        this.bettingBoard.put(player, new BettingMoney(bettingMoney));
    }
    
    public Money getProfit(Player player, WinningStatus winningStatus) {
        return bettingBoard.get(player).getProfit(winningStatus);
    }
    
    public int getDealerProfit(Map<Player, WinningStatus> playerWinningStatus) {
        List<Money> amounts = playerWinningStatus.keySet().stream()
                .map(player -> getProfit(player, playerWinningStatus.get(player)))
                .toList();
        
        return -Money.getTotalMoney(amounts);
    }
}
