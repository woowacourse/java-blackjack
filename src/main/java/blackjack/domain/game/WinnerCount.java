package blackjack.domain.game;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.HashMap;
import java.util.Map;

public class WinnerCount {
    private final Map<WinnerFlag, Integer> winnerCount;

    public WinnerCount() {
        this.winnerCount = new HashMap<>(3);
        winnerCount.put(WinnerFlag.WIN, 0);
        winnerCount.put(WinnerFlag.DRAW, 0);
        winnerCount.put(WinnerFlag.LOSE, 0);
    }

    public Map<WinnerFlag, Integer> calculateTotalWinnings(Players players) {
        for (Player player : players.getPlayers()) {
            winnerCount.computeIfPresent(player.getResult(), (WinnerFlag, i) -> ++i);
        }
        return winnerCount;
    }
}
