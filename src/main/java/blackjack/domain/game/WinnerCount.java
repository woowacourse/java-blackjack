package blackjack.domain.game;

import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;

import java.util.EnumMap;
import java.util.Map;

import static blackjack.domain.game.WinnerFlag.*;

public class WinnerCount {
    private final Map<WinnerFlag, Integer> winnerCount;

    public WinnerCount() {
        this.winnerCount = new EnumMap<>(WinnerFlag.class);
        winnerCount.put(WIN, 0);
        winnerCount.put(DRAW, 0);
        winnerCount.put(LOSE, 0);
    }

    public Map<WinnerFlag, Integer> calculateTotalWinnings(Players players) {
        for (Player player : players.toList()) {
            winnerCount.put(player.getResult(), winnerCount.getOrDefault(player.getResult(), 0) + 1);
        }
        return winnerCount;
    }
}
