package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final int PLAYER_LIMIT = 9;
    private static final String PLAYER_LIMIT_ERROR_MESSAGE = "참여자는 최대 9명까지 가능합니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> players, CardDistributor distributor) {
        validatePlayerCount(players.size());
        return new Players(initializePlayers(players, distributor));
    }

    private static void validatePlayerCount(int size) {
        if (size > PLAYER_LIMIT) {
            throw new IllegalArgumentException(PLAYER_LIMIT_ERROR_MESSAGE);
        }
    }

    private static List<Player> initializePlayers(List<String> playerNames, CardDistributor distributor) {
        return playerNames.stream()
                .map(String::trim)
                .map(name -> createPlayer(distributor, name))
                .collect(Collectors.toList());
    }

    private static Player createPlayer(CardDistributor distributor, String name) {
        return new Player(new Name(name), new Cards(distributor.distributeInitialCard()));
    }

}
