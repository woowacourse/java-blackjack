package blackjack.domain;

import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Nickname> nicknames) {
        this.players = nicknames.stream()
                .map(Player::new)
                .toList();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
