package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final int PLAYER_LIMIT = 9;
    private static final String PLAYER_LIMIT_ERROR_MESSAGE = "참여자는 최대 9명까지 가능합니다.";
    private static final String PLAYER_DUPLICATE_NAME_ERROR_MESSAGE = "참여자 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames, CardDistributor distributor) {
        playerNames = playerNames.stream()
                .map(String::trim)
                .collect(Collectors.toList());
        validate(playerNames);
        return new Players(initializePlayers(playerNames, distributor));
    }

    private static void validate(List<String> playerNames) {
        validatePlayerCount(playerNames.size());
        validateDuplicateName(playerNames);
    }

    private static void validatePlayerCount(int size) {
        if (size > PLAYER_LIMIT) {
            throw new IllegalArgumentException(PLAYER_LIMIT_ERROR_MESSAGE);
        }
    }

    private static void validateDuplicateName(List<String> playerNames) {
        long deduplicatedNameCount = playerNames.stream()
                .distinct()
                .count();

        if (deduplicatedNameCount != playerNames.size()) {
            throw new IllegalArgumentException(PLAYER_DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }

    private static List<Player> initializePlayers(List<String> playerNames, CardDistributor distributor) {
        return playerNames.stream()
                .map(playerName -> createPlayer(distributor, playerName))
                .collect(Collectors.toList());
    }

    private static Player createPlayer(CardDistributor distributor, String playerName) {
        return new Player(new Name(playerName), distributor.distributeInitialCard());
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

}
