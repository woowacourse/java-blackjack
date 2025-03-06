package domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerRepository {

    private static final PlayerRepository INSTANCE = new PlayerRepository();

    private final Map<String, Player> repository = new LinkedHashMap<>();

    private PlayerRepository() {
    }

    public static PlayerRepository getInstance() {
        return INSTANCE;
    }

    public Player getByName(String name) { // TODO : 존재하지 않을 경우 예외처리
        return repository.get(name);
    }

    public void addAll(List<Player> players) { // TODO : 중복 될 경우 예외처리
        players.forEach(
                player -> repository.put(player.getName(), player)
        );
    }

    public List<Player> getAll() {
        return repository.keySet().stream()
                .map(repository::get)
                .toList();
    }

    public void clear() {
        repository.clear();
    }
}
