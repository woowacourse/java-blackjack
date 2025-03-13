package blackjack.domain;

import blackjack.domain.money.BettingMoney;
import blackjack.domain.player.Player;

import java.util.Map;

public class BettingBoard {
    
    private final Map<Player, BettingMoney> bettingBoard;
    
    public BettingBoard(final Map<Player, BettingMoney> bettingBoard) {
        this.bettingBoard = bettingBoard;
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
