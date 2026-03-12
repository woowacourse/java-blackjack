package domain.participant;

import exception.BlackjackException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Players {

    private static final int PLAYER_MIN_COUNT = 2;
    private static final int PLAYER_MAX_COUNT = 8;
    public static final String PLAYER_DUPLICATED = "게임 참가자의 이름은 중복 되어선 안됩니다.";
    public static final String PLAYER_COUNT_OUT_OF_RANGE =
            String.format("게임 참가자의 수는 %d~%d명 사이여야 합니다.", PLAYER_MIN_COUNT, PLAYER_MAX_COUNT);

    private final List<Player> players;

    public Players(List<String> names) {
        validatePlayerNames(names);
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(name));
        }
        this.players = players;
    }

    private void validatePlayerNames(List<String> names) {
        validatePlayerDuplication(names);
        validatePlayerCount(names.size());
    }

    private void validatePlayerDuplication(List<String> names) {
        if (new HashSet<>(names).size() != names.size()) {
            throw new BlackjackException(PLAYER_DUPLICATED);
        }
    }

    private void validatePlayerCount(int playerCount) {
        if (!(PLAYER_MIN_COUNT <= playerCount && playerCount <= PLAYER_MAX_COUNT)) {
            throw new BlackjackException(PLAYER_COUNT_OUT_OF_RANGE);
        }
    }

    public Player getPlayer(String name) {
        return players.stream()
                .filter(player -> player.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
