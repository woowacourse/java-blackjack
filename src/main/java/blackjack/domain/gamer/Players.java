package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public List<Player> getUnmodifiableList() {
        return Collections.unmodifiableList(players);
    }

    public List<String> names() {
        return players.stream()
            .map(Player::getName)
            .collect(Collectors.toList());
    }

}
