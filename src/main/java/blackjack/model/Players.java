package blackjack.model;

import java.util.Arrays;
import java.util.List;

public class Players {
    private static final String DELIMITER = ",";
    private static final int INCLUDE_EMPTY_ELEMENT = -1;

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(String rawPlayerNames, AceAdjustPolicy aceAdjustPolicy) {
        List<String> playerNames = Arrays.asList(rawPlayerNames.split(DELIMITER, INCLUDE_EMPTY_ELEMENT));
        validateDuplicatedNames(playerNames);

        List<Player> players = playerNames.stream()
                .map(name -> new Player(name, new Hand(aceAdjustPolicy))).toList();
        return new Players(players);
    }

    private static void validateDuplicatedNames(List<String> playerNames) {
        if (playerNames.stream().distinct().count() != playerNames.size()) {
            throw new IllegalArgumentException("참가자의 이름은 중복될 수 없습니다.");
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
