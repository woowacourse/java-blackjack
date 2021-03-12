package blackjack.domain.participant;

import blackjack.domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Map<String, Integer> getPlayersProfitResult(final int dealerScore) {
        return players.stream()
                .collect(toMap(Player::getName, player -> player.getProfit(dealerScore)));
    }
}