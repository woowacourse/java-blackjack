package domain.gambler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class Players {
    private static final int MAXIMUM_PLAYER_COUNT = 7;
    private static final int MINIMUM_PLAYER_COUNT = 1;
    private static final String NULL_EXCEPTION_MESSAGE = "Null exception.";
    private static final String OUT_OF_RANGE_EXCEPTION_MESSAGE = "Player number out of range exception. (1,7)";

    private final List<Player> players;

    public Players(Map<Name, Money> playerInfo) {
        validatePlayerInfo(playerInfo);
        this.players = playerInfo.entrySet()
                .stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue()))
                .collect(collectingAndThen(
                        toList(),
                        Collections::unmodifiableList));
    }

    private void validatePlayerInfo(Map<Name, Money> playerInfo) {
        if (Objects.isNull(playerInfo)) {
            throw new IllegalArgumentException(NULL_EXCEPTION_MESSAGE);
        }
        if (playerInfo.size() < MINIMUM_PLAYER_COUNT || playerInfo.size() > MAXIMUM_PLAYER_COUNT) {
            throw new IllegalArgumentException(OUT_OF_RANGE_EXCEPTION_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .map(Name::toString)
                .collect(collectingAndThen(
                        toList(),
                        Collections::unmodifiableList));
    }
}
