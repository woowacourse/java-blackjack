package blackjack.domain.participant;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

public class Players {
    private final List<Player> players;

    public Players(Map<String, Integer> playerInformation) {
        this.players = playerInformation.entrySet().stream()
                .map(entry -> new Player(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<String, Integer> getPlayersProfitResult(final Dealer dealer) {
        return players.stream()
                .collect(toMap(Player::getName, player -> player.getProfit(dealer)));
    }
}