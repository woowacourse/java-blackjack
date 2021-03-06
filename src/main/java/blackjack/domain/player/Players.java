package blackjack.domain.player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class Players implements Iterable<Player> {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public Players getGamblers() {
        return players.stream()
                .filter(player -> player instanceof Gambler)
                .collect(Collectors.collectingAndThen(toList(), Players::new));
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
