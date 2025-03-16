package blackjack.gambler;

import blackjack.constant.MatchResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public Map<Player, MatchResult> deriveResults(int dealerScore) {
        return players.stream()
                .collect(Collectors.toMap(
                        player -> player,
                        player -> player.compareTo(dealerScore),
                        (x, y) -> y, LinkedHashMap::new
                ));
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    public List<String> getPlayerNames() {
        return new ArrayList<>(players.stream()
                .map(Player::getNickname)
                .toList());
    }
}
