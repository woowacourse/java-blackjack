package domain;

import domain.user.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerResultRepository {
    private final Map<Player, Result> repository;

    public PlayerResultRepository() {
        this.repository = new HashMap<>();
    }

    public void save(Player player, Result result) {
        repository.put(player, result);
    }

    public Result findByPlayer(Player player) {
        return repository.get(player);
    }

    public Map<Player, Result> getRepository() {
        return repository;
    }
}
