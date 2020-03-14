package blackjack.domain.gamer;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Players implements Iterable<Player> {

    private List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> names) {
        List<Player> players = names.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Players(players);
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
