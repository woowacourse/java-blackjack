package domain.blackjackgame;

import domain.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, Integer> playerProfits;

    public GameResult() {
        this.playerProfits = new LinkedHashMap<>();
    }

    public void record(Player player, int profit) {
        playerProfits.put(player, profit);
    }

    public int getDealerResult() {
        int profitSum = playerProfits.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        return reverse(profitSum);
    }

    private int reverse(int value) {
        return -value;
    }

    public Map<Player, Integer> getPlayerResult() {
        return Collections.unmodifiableMap(this.playerProfits);
    }
}
