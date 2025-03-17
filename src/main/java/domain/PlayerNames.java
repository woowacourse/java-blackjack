package domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class PlayerNames {
    public static final int MAX_PLAYER_QUANTITY = 5;

    private final List<PlayerName> playerNames;

    public PlayerNames(List<PlayerName> playerNames) {
        validatePlayers(playerNames);
        this.playerNames = playerNames;
    }

    private void validatePlayers(List<PlayerName> playerNames) {
        validateDuplicate(playerNames);
        validatePlayerQuantity(playerNames);
    }

    private void validatePlayerQuantity(List<PlayerName> playerNames) {
        if (playerNames.size() > MAX_PLAYER_QUANTITY) {
            throw new IllegalArgumentException("플레이어는 최대 5명까지 참가 가능합니다.");
        }
    }

    private void validateDuplicate(List<PlayerName> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복일 수 없습니다.");
        }
    }

    public List<PlayerName> getPlayerNames() {
        return new ArrayList<>(playerNames);
    }
}
