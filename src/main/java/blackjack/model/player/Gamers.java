package blackjack.model.player;

import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.player.matcher.ResultIdentifier;
import blackjack.model.player.matcher.Record;
import blackjack.model.player.matcher.Result;
import java.util.Collection;
import java.util.List;

public class Gamers {

    private final List<Gamer> players;

    public Gamers(List<Gamer> players) {
        this.players = List.copyOf(players);
    }

    public List<Record> match(Dealer dealer) {
        ResultIdentifier resultIdentifier = new ResultIdentifier();
        return players.stream()
            .map(player -> createRecord(resultIdentifier, dealer, player))
            .collect(toUnmodifiableList());
    }

    private Record createRecord(ResultIdentifier classifier, Dealer dealer, Gamer gamer) {
        Result result = classifier.identify(dealer, gamer);
        return new Record(gamer.name(), result);
    }

    public Collection<Gamer> values() {
        return List.copyOf(players);
    }
}
