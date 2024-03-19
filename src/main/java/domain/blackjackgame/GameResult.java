package domain.blackjackgame;

import domain.participant.Player;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, BigDecimal> playerProfits;

    public GameResult() {
        this.playerProfits = new LinkedHashMap<>();
    }

    public void record(Player player, BigDecimal profit) {
        playerProfits.put(player, profit);
    }

    public BigDecimal getDealerResult() {
        BigDecimal profitSum = playerProfits.values().stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return reverse(profitSum);
    }

    private BigDecimal reverse(BigDecimal value) {
        return value.negate();
    }

    public Map<Player, BigDecimal> getPlayerResult() {
        return Collections.unmodifiableMap(this.playerProfits);
    }
}
