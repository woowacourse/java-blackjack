package blackjack.model.player;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class Players implements Iterable<Player> {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    private Players(Player player, Player... players) {
        this.players = Stream.concat(Stream.of(player), Stream.of(players))
            .collect(toUnmodifiableList());
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    public Stream<Player> stream() {
        return players.stream();
    }

    public static Players of(Player player ,Player... players) {
        return new Players(player, players);
    }
}
