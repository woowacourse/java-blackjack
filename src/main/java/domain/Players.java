package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Players {

    private static final int MAX_PLAYERS_SIZE = 5;
    private static final String SIZE_ERROR_GUIDE_MESSAGE = "[ERROR] 플레어이는 5명까지 참가 가능합니다.";
    private static final String DUPLICATE_ERROR_GUIDE_MESSAGE = "[ERROR] 플레이어 이름은 중복일 수 없습니다.";

    private final List<Player> players;

    public Players(List<String> playerNames) {
        validatePlayers(playerNames);
        this.players = createPlayers(playerNames);
    }

    private void validatePlayers(List<String> playerNames) {
        validateSameName(playerNames);
        validateSize(playerNames);
    }

    private List<Player> createPlayers(List<String> list) {
        List<Player> players = new ArrayList<>();
        for (String playerName : list) {
            players.add(new Player(new PlayerName(playerName), new Cards(new ArrayList<>())));
        }
        return players;
    }

    private void validateSameName(List<String> playerNames) {
        int removeDuplicateSize = new HashSet<>(playerNames).size();
        if (playerNames.size() != removeDuplicateSize) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_GUIDE_MESSAGE);
        }
    }

    private void validateSize(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYERS_SIZE) {
            throw new IllegalArgumentException(SIZE_ERROR_GUIDE_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
