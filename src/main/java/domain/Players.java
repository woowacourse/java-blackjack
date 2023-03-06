package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final String PLAYER_DUPLICATE_NAME_ERROR_MESSAGE = "참여자 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames, List<Cards> cards) {
        List<Name> names = playerNames.stream()
                .map(String::trim)
                .map(Name::new)
                .collect(Collectors.toList());
        validateDuplicateName(names);
        return new Players(initializePlayers(names, cards));
    }

    private static void validateDuplicateName(List<Name> playerNames) {
        long deduplicatedNameCount = playerNames.stream()
                .distinct()
                .count();

        if (deduplicatedNameCount != playerNames.size()) {
            throw new IllegalArgumentException(PLAYER_DUPLICATE_NAME_ERROR_MESSAGE);
        }
    }

    private static List<Player> initializePlayers(List<Name> playerNames, List<Cards> cards) {
        List<Player> players = new ArrayList<>();
        for (int index = 0; index < playerNames.size(); index++) {
            players.add(Player.of(playerNames.get(index), cards.get(index)));
        }
        return players;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }

}
