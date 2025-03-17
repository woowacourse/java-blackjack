package blackjack.domain;

import blackjack.domain.money.BlackjackBettingMoney;
import blackjack.domain.money.Money;
import blackjack.domain.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class BettingBoard {
    
    private final Map<Player, BlackjackBettingMoney> bettingBoard;
    
    public BettingBoard() {
        this.bettingBoard = new HashMap<>();
    }
    
    public void bet(Player player, BlackjackBettingMoney bettingMoney) {
        this.bettingBoard.put(player, bettingMoney);
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
