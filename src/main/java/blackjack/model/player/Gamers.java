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
        return players.stream()
            .map(player -> matchEachPlayer(dealer, player))
            .collect(toUnmodifiableList());
    }

    public Record matchEachPlayer(Dealer dealer, Gamer gamer) {
        Matcher matcher = Matcher.of(dealer, gamer);
        return matcher.match();
    }

    public Collection<Gamer> values() {
        return List.copyOf(players);
    }
}
