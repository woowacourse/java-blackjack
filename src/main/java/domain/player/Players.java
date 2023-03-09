package domain.player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class Players {

    private static final int MAX_PLAYERS_SIZE = 5;
    private static final String SIZE_ERROR_GUIDE_MESSAGE = "[ERROR] 플레어이는 5명까지 참가 가능합니다.";
    private static final String DUPLICATE_ERROR_GUIDE_MESSAGE = "[ERROR] 플레이어 이름은 중복일 수 없습니다.";

    private final List<Player> players;

    public Players(final List<String> playerNames) {
        validatePlayers(playerNames);
        this.players = createPlayers(playerNames);
    }

    private void validatePlayers(final List<String> playerNames) {
        validateSameName(playerNames);
        validateSize(playerNames);
    }

    private void validateSameName(final List<String> playerNames) {
        int removeDuplicateSize = new HashSet<>(playerNames).size();
        if (playerNames.size() != removeDuplicateSize) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_GUIDE_MESSAGE);
        }
    }

    private void validateSize(final List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYERS_SIZE) {
            throw new IllegalArgumentException(SIZE_ERROR_GUIDE_MESSAGE);
        }
    }

    private List<Player> createPlayers(final List<String> players) {
        return players.stream()
                .map(name -> new Player(new PlayerName(name)))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
