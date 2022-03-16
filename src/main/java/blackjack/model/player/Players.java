package blackjack.model.player;

import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.blackjack.Record;
import java.util.Collection;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public List<Record> match(Dealer dealer) {
        return players.stream()
            .map(dealer::match)
            .collect(toUnmodifiableList());
    }

    public Collection<Player> values() {
        return List.copyOf(players);
    }
}
