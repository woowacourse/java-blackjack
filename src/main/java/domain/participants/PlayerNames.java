package domain.participants;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerNames {
    private final List<PlayerName> playerNames;

    private PlayerNames(List<PlayerName> playerNames) {
        this.playerNames = playerNames;
    }

    public static PlayerNames fromUsernames(List<String> usernames) {
        List<PlayerName> playerNames = usernames.stream()
                .map(PlayerName::new)
                .toList();
        validateDuplicate(playerNames);
        return new PlayerNames(playerNames);
    }

    public List<PlayerName> getPlayerNames() {
        return Collections.unmodifiableList(playerNames);
    }

    private static void validateDuplicate(List<PlayerName> playerNames) {
        Set playerNamesSet = new HashSet(playerNames);
        if (playerNamesSet.size() != playerNames.size()) {
            throw new IllegalArgumentException("중복된 이름이 있습니다.");
        }
    }

}
