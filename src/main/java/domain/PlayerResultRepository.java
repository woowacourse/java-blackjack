package domain;

import domain.user.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerResultRepository {
    private final static Map<Player, Result> repository = new HashMap<>();

    public void save(Player player, Result result) {
        repository.put(player, result);
    }

    public Result findByPlayer(Player player) {
        return repository.get(player);
    }
}
