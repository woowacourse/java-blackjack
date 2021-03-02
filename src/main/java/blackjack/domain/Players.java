package blackjack.domain;

import java.util.*;
import java.util.stream.Collectors;

public class Players {
    private static final String DELIMITER = ",";
    private final Set<Player> players;

    private Players(Set<Player> players) {
        this.players = new HashSet<>(players);
    }

    public static Players valueOf(String unParsedNames) {
        List<Player> parsedPlayers = Arrays.stream(unParsedNames.split(DELIMITER))
                .map(Player::new)
                .collect(Collectors.toList());
        validateDuplication(parsedPlayers);
        return new Players(new HashSet<>(parsedPlayers));
    }

    private static void validateDuplication(List<Player> players) {
        if (players.size() != new HashSet<>(players).size()) {
            throw new IllegalArgumentException("중복된 이름은 사용할 수 없습니다.");
        }
    }
}
