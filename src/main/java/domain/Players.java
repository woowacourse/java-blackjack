package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final String PLAYER_DUPLICATE_NAME_ERROR_MESSAGE = "참여자 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        List<Name> playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        validateDuplicateName(playerNames);
        return new Players(players);
    }

    private static void validateDuplicateName(List<Name> playerNames) {
        long deduplicatedNameCount = playerNames.stream()
                .distinct()
                .count();

        if (deduplicatedNameCount != playerNames.size()) {
            throw new IllegalArgumentException(PLAYER_DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

    public List<String> getPlayerNamesToString() {
        return players.stream()
                .map(Player::getNameToValue)
                .collect(Collectors.toList());
    }

    public List<Name> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }

}
