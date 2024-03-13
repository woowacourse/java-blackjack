package domain;

import domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bettings {

    private final Map<Player, BetAmount> playersBetting;

    public Bettings() {
        this.playersBetting = new LinkedHashMap<>();
    }

    public void save(final Player player, final BetAmount betAmount) {
        playersBetting.put(player, betAmount);
    }

    public BetAmount findBy(final Player player) {
        return playersBetting.get(player);
    }
}
