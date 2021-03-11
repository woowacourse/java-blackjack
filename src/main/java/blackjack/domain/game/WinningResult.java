package blackjack.domain.game;

import blackjack.domain.player.Player;

import java.util.HashMap;
import java.util.Map;

public class WinningResult {
    Map<Player, WinOrLose> winningTable = new HashMap<>();

    public void add(Player player, WinOrLose winOrLose){
        winningTable.put(player, winOrLose);
    }

    public WinOrLose get(Player player){
        return winningTable.get(player);
    }
}