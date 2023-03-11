package domain;

import domain.user.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerRevenues {
    private final Map<Player, Integer> repository;

    public PlayerRevenues() {
        this.repository = new HashMap<>();
    }

    public void save(Player player, Integer result) {
        repository.put(player, result);
    }

    public Integer findByPlayer(Player player) {
        return repository.get(player);
    }

    public Map<Player, Integer> getRepository() {
        return repository;
    }

    public long dealerRevenue() {
        return repository.values().stream().mapToInt(value -> -value).reduce(0, Integer::sum);
    }
}
