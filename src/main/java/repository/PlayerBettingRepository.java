package repository;

import domain.model.Player;
import domain.model.PlayerBetting;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerBettingRepository {

    private final Map<Player, PlayerBetting> playerBettings = new LinkedHashMap<>();

    public PlayerBetting save(PlayerBetting playerBetting) {
        playerBettings.put(playerBetting.getPlayer(), playerBetting);
        return playerBetting;
    }
}
