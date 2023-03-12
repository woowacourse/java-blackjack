package domain.participants;

import java.util.List;

public class Players {
    private static final String INVALID_NAME = "중복된 이름입니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        validateDuplicatedName(players);
        this.players = players;
    }

    private void validateDuplicatedName(List<Player> splitedName) {
        if (splitedName.size() != splitedName.stream().map(Player::getName).distinct().count()) {
            throw new IllegalArgumentException(INVALID_NAME);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
