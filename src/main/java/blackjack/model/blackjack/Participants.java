package blackjack.model.blackjack;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.card.Card;
import blackjack.model.player.Dealer;
import blackjack.model.player.Name;
import blackjack.model.player.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Participants {

    private final Dealer dealer;
    private final List<Player> players;

    public Participants(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public List<Record> records() {
        return Stream.concat(Stream.of(dealerRecord()), playerRecords().stream())
            .collect(toUnmodifiableList());
    }

    private Record dealerRecord() {
        Map<Result, Long> dealerRecord = players.stream()
            .map(dealer::match)
            .collect(groupingBy(r -> r, counting()));
        return new Record(Dealer.dealerName(), dealerRecord);
    }

    private List<Record> playerRecords() {
        return players.stream().map(this::playerRecord).collect(toUnmodifiableList());
    }

    private Record playerRecord(Player player) {
        Result result = dealer.match(player);
        return new Record(player.name(), result.reverse());
    }

    public void takeCardByName(Name name, Card card) {
        Player player = findByName(name);
        player.take(card);
    }

    private Player findByName(Name name) {
        if (name.equals(Dealer.dealerName())) {
            return dealer;
        }
        return players.stream().filter(player -> player.name().equals(name)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
