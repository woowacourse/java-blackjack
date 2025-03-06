package domain;

import static util.ExceptionConstants.*;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PlayerRepository {

    private static final PlayerRepository INSTANCE = new PlayerRepository();
    private static final String DUPLICATE_NAME = "중복되는 이름이 있습니다.";

    private final Map<String, Player> repository = new LinkedHashMap<>();

    private PlayerRepository() {
    }

    public static PlayerRepository getInstance() {
        return INSTANCE;
    }

    public Player getByName(String name) {
        Player player = repository.get(name);
        validateExistedPlayer(player);
        return player;
    }

    public void addAll(List<Player> players) {
        validateDuplicateName(players);
        players.forEach(player ->
                repository.put(player.getName(), player)
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

    private void validateExistedPlayer(Player player) {
        if (player == null) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicateName(List<Player> players) {
        if (players.size() != new HashSet<>(players).size()) {
            throw new IllegalArgumentException(ERROR_HEADER + DUPLICATE_NAME);
        }
    }
}
