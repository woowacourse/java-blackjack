package blackjack.model.player;

import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.player.matcher.Matcher;
import blackjack.model.player.matcher.Record;
import java.util.Collection;
import java.util.List;

public class Gamers {

    private final List<Gamer> players;

    public Gamers(List<Gamer> players) {
        this.players = List.copyOf(players);
    }

    public List<Record> match(Dealer dealer) {
        Matcher matcher = Matcher.of(dealer);
        return players.stream()
            .map(matcher::match)
            .collect(toUnmodifiableList());
    }

    public Collection<Gamer> values() {
        return List.copyOf(players);
    }
}
