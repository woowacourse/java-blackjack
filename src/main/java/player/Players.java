package player;

import java.util.List;

public class Players {

    private static final int MINIMUM_PLAYER_RANGE = 2;
    private static final int MAXIMUM_PLAYER_RANGE = 8;

    private final List<Player> players;

    public Players(List<Player> players) {
        validate(players);
        this.players = players;
    }

    private void validate(List<Player> players) {
        validatePlayerCountRange(players);
    }

    private void validatePlayerCountRange(List<Player> players) {
        if (players.size() < MINIMUM_PLAYER_RANGE || MAXIMUM_PLAYER_RANGE < players.size()) {
            throw new IllegalArgumentException(
                    "참가자의 인원은 최소 " + MINIMUM_PLAYER_RANGE + "에서 최대 " + MAXIMUM_PLAYER_RANGE + "명 까지 가능합니다.");
        }
    }
}
