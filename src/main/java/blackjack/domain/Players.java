package blackjack.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
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
