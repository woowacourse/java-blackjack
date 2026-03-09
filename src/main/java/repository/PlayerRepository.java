package repository;

import domain.model.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerRepository {

    private Map<String, Player> players = new HashMap<>();

    public Player save(Player player) {
        players.put(player.getName(), player);
        return player;
    }

    public void saveAll(List<Player> players) {
        players.forEach(player -> this.players.put(player.getName(), player));
    }

    public List<Player> findAll() {
        return players.values().stream().toList();
    }
}
