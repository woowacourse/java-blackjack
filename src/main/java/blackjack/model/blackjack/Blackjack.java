package blackjack.model.blackjack;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.cards.Cards;
import blackjack.model.player.Dealer;
import blackjack.model.player.Name;
import blackjack.model.player.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Blackjack {

    private CardDispenser cardDispenser;
    private Dealer dealer;
    private List<Player> players;

    public Blackjack(CardDispenser cardDispenser, List<Name> names) {
        this.cardDispenser = cardDispenser;
        this.dealer = createDealer();
        this.players = createPlayers(cardDispenser, names.toArray(Name[]::new));
    }

    public Blackjack(CardDispenser cardDispenser, Name... names) {
        this.cardDispenser = cardDispenser;
        this.dealer = createDealer();
        this.players = createPlayers(cardDispenser, names);
    }

    private Dealer createDealer() {
        return new Dealer(cardDispenser.issue(), cardDispenser.issue());
    }

    private List<Player> createPlayers(CardDispenser cardDispenser, Name... names) {
        return Arrays.stream(names)
            .map(name -> createPlayer(cardDispenser, name))
            .collect(toUnmodifiableList());
    }

    private Player createPlayer(CardDispenser cardDispenser, Name name) {
        return new Player(name, cardDispenser.issue(), cardDispenser.issue());
    }

    public void takeCardByName(Name name) {
        findPlayerByName(name).take(cardDispenser.issue());
    }

    public Records records() {
        return new Records(recordsMap());
    }

    private Map<Name, Record> recordsMap() {
        return Stream.concat(Stream.of(dealerRecord()), playerRecords().stream())
            .collect(toMap(Record::name, record -> record));
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

    public boolean isHittableByName(Name name) {
        return findPlayerByName(name).isHittable();
    }

    public Cards ownCardsByName(Name name) {
        return findPlayerByName(name).cards();
    }

    public Cards openedCardsByName(Name name) {
        return findPlayerByName(name).openCards();
    }

    public Score scoreByName(Name name) {
        return findPlayerByName(name).score();
    }

    private Player findPlayerByName(Name name) {
        if (name.equals(Dealer.dealerName())) {
            return dealer;
        }
        return players.stream()
            .filter(player -> player.isSameName(name))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
