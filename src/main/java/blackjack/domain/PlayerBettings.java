package blackjack.domain;

import java.util.List;
import java.util.Map;

public class PlayerBettings {
    private final List<PlayerBetting> playerBettings;

    public PlayerBettings(final List<PlayerBetting> playerBettings) {
        this.playerBettings = playerBettings;
    }

    public static PlayerBettings from(final Map<String, Integer> rawPlayerBettings) {
        List<PlayerBetting> playerBettings = rawPlayerBettings.entrySet().stream().
                map(entry -> new PlayerBetting(entry.getKey(), entry.getValue()))
                .toList();
        return new PlayerBettings(playerBettings);
    }

    public List<PlayerBetting> getPlayerBettings() {
        return playerBettings;
    }
}
