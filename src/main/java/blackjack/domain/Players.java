package blackjack.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private static final int MIN_PLAYER_SIZE = 1;
    private static final int MAX_PLAYER_SIZE = 4;
    private final List<Player> players;

    public Players(List<Player> players) {
        validateSize(players);
        this.players = players;
    }

    private static void validateSize(List<Player> players) {
        int size = players.size();
        if (size < MIN_PLAYER_SIZE || size > MAX_PLAYER_SIZE) {
            throw new IllegalArgumentException(
                    String.format("플레이어의 수는 %d ~ %d명 이어야 한다.", MIN_PLAYER_SIZE, MAX_PLAYER_SIZE));
        }
    }

    public static Players convertTo(List<String> playerNames) {
        return new Players(playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList()));
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<Player, Integer> calculate() {
        return players.stream()
                .collect(Collectors.toMap(player -> player, player -> player.getHand().calculate(),
                        (player1, player2) -> player2, LinkedHashMap::new));
    }
}
