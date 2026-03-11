package repository;

import domain.model.Player;
import domain.model.PlayerBetting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerBettingRepository {

    private final List<PlayerBetting> playerBettings = new ArrayList<>();

    public PlayerBetting save(PlayerBetting playerBetting) {
        playerBettings.add(playerBetting);
        return playerBetting;
    }

    public Optional<PlayerBetting> findByPlayer(Player player) {
        return playerBettings.stream()
                .filter(playerBetting -> playerBetting.isSamePlayer(player))
                .findFirst();
    }
}
