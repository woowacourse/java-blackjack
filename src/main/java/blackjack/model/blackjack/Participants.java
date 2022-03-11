package blackjack.model.blackjack;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.blackjack.CardDispenser;
import blackjack.model.blackjack.Record;
import blackjack.model.blackjack.Result;
import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Participants {
    private final Dealer dealer;
    private final List<Player> players;

    public Participants(CardDispenser cardDispenser, String... names) {
        this.dealer = new Dealer(cardDispenser.issue(), cardDispenser.issue());
        this.players = Arrays.stream(names).map(name -> createPlayer(cardDispenser, name)).collect(toUnmodifiableList());
    }

    private Player createPlayer(CardDispenser cardDispenser, String name) {
        return new Player(name, cardDispenser.issue(), cardDispenser.issue());
    }

    public List<Record> records() {
        return Stream.concat(Stream.of(dealerRecord()), playerRecords().stream())
            .collect(toUnmodifiableList());
    }

    private Record dealerRecord() {
        Map<Result, Long> dealerRecord = players.stream()
            .map(dealer::match)
            .collect(groupingBy(r -> r, counting()));
        return new Record("딜러", dealerRecord);
    }

    private List<Record> playerRecords() {
        return players.stream().map(this::playerRecord).collect(toUnmodifiableList());
    }

    private Record playerRecord(Player player) {
        Result result = dealer.match(player);
        return new Record(player.name(), result.reverse());
    }
}
