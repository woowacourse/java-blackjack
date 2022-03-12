package blackjack.model.blackjack;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toUnmodifiableList;

import blackjack.model.cards.Cards;
import blackjack.model.cards.Score;
import blackjack.model.player.Dealer;
import blackjack.model.player.Name;
import blackjack.model.player.Player;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Blackjack {

    private CardDispenser cardDispenser;
    private Dealer dealer;
    private List<Player> players;

    public Blackjack(CardDispenser cardDispenser, List<Name> names) {
        this(cardDispenser, names.toArray(Name[]::new));
    }

    Blackjack(CardDispenser cardDispenser, Name... names) {
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

    private Player findPlayerByName(Name name) {
        if (isDealerName(name)) {
            return dealer;
        }
        return findGamer(name);
    }

    private boolean isDealerName(Name name) {
        return dealer.isSameName(name);
    }

    private Player findGamer(Name name) {
        return players.stream()
            .filter(player -> player.isSameName(name))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException(String.format("%s의 이름을 가진 플레이어가 없습니다.", name)));
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

    public Records records() {
        Map<Name, Result> results = players.stream()
            .collect(toMap(Player::name, this::eachPlayerResult));
        return new Records(results);
    }

    private Result eachPlayerResult(Player p) {
        Result result = dealer.match(p);
        return result.reverse();
    }
}
