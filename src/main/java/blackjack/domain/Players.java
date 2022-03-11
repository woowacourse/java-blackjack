package blackjack.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {

    public static final String DEALER = "딜러";

    private final List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public Player getDealer() {
        return players.stream()
                .filter(player -> player.getName().equals(DEALER))
                .findAny()
                .orElseThrow();
    }
}
