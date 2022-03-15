package blackjack.domain.entry;

import blackjack.domain.PlayerOutcome;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Map<PlayerOutcome, List<Player>> match(Dealer dealer) {
        return players.stream()
                .collect(Collectors.groupingBy(player -> player.match(dealer)));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
