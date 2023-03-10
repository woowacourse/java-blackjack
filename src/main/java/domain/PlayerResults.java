package domain;

import domain.user.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerResults {
    private final Map<Player, Result> repository;

    public PlayerResults() {
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

    public long playerWinCount() {
        return repository.values().stream().filter(result -> result == Result.WIN).count();
    }

    public long playerLoseCount() {
        return repository.values().stream().filter(result -> result == Result.LOSE).count();
    }

    public long playerDrawCount() {
        return repository.values().stream().filter(result -> result == Result.DRAW).count();
    }
}
