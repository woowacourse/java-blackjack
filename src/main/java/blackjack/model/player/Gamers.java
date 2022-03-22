package blackjack.model.player;

import static java.util.stream.Collectors.toUnmodifiableList;

import java.util.Collection;
import java.util.List;

public class Gamers {

    private final List<Gamer> players;

    public Gamers(List<Gamer> players) {
        this.players = List.copyOf(players);
    }

    public Records match(Dealer dealer) {
        return new Records(collectRecord(dealer));
    }

    private List<Record> collectRecord(Dealer dealer) {
        ResultIdentifier resultIdentifier = new ResultIdentifier();
        return players.stream()
            .map(player -> createRecord(resultIdentifier, dealer, player))
            .collect(toUnmodifiableList());
    }

    private Record createRecord(ResultIdentifier classifier, Dealer dealer, Gamer gamer) {
        Result result = classifier.identify(dealer, gamer);
        return new Record(gamer, result);
    }

    public Collection<Gamer> values() {
        return List.copyOf(players);
    }
}
