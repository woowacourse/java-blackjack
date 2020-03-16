package domain.user;

import domain.Names;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(Names names) {
        players = names.get()
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> get() {
        return Collections.unmodifiableList(players);
    }
}
