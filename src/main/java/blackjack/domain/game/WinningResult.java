package blackjack.domain.game;

import blackjack.domain.player.Player;

import java.util.HashMap;
import java.util.Map;

public class WinningResult {
    final Map<Player, WinOrLose> winningTable = new HashMap<>();

    public void add(final Player player, final WinOrLose winOrLose) {
        winningTable.put(player, winOrLose);
    }

    public WinOrLose get(final Player player) {
        return winningTable.get(player);
    }
}