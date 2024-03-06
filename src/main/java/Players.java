import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 8;

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validate(playerNames);
        players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName));
        }
    }

    private void validate(List<String> players) {
        validateSize(players.size());
        validateDuplicatedNames(players);
    }

    private void validateDuplicatedNames(List<String> players) {
        long nonDuplicatedCount = players.stream()
                .distinct()
                .count();
        if (nonDuplicatedCount != players.size()) {
            throw new IllegalArgumentException("[ERROR] 플레이어 간 중복된 이름을 가질 수 없습니다.");
        }
    }

    private void validateSize(int size) {
        if (size < MIN_SIZE || size > MAX_SIZE) {
            throw new IllegalArgumentException("[ERROR] 플레이어는 최소 " + MIN_SIZE + "명에서 최대 " + MAX_SIZE + "명까지 참여할 수 있습니다.");
        }
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
